package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.samuel.barbershopapplication.backendservices.api.ApiAuthService
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.model.ApiAuthRequest
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val apiAuthService: ApiAuthService,
  private val sharedPrefsService: SharedPrefsService
) : ViewModel() {
  private val _email = mutableStateOf("")
  val email: MutableState<String> = _email
  private val _password = mutableStateOf("")
  val password: MutableState<String> = _password

  private val _passwordError = mutableStateOf("")
  val passwordError: MutableState<String> = _passwordError

  private val _emailError = mutableStateOf("")
  val emailError: MutableState<String> = _emailError

  private val _error = mutableStateOf("")
  val error: MutableState<String> = _passwordError

  private val _isLoading = mutableStateOf(false)
  val isLoading: MutableState<Boolean> = _isLoading

  fun onClickButtonLogin(navController: NavController) {
    viewModelScope.launch {
      _emailError.value = ""
      _passwordError.value = ""
      _error.value = ""
      _isLoading.value = true

      if (email.value.isBlank()) {
        _emailError.value = "Preencha o email"
        isLoading.value = false
        return@launch
      }
      if (password.value.isBlank()) {
        _passwordError.value = "Preencha a senha"
        isLoading.value = false
        return@launch
      }

      val apiAuthRequest = ApiAuthRequest(_email.value, _password.value)
      try {
        val response = apiAuthService.login(apiAuthRequest)
        if (response.isSuccessful) {
          val token = response.body()?.token
          val userId = response.body()?.id
          val name = response.body()?.name.toString()
          val email = response.body()?.email.toString()
          if (token != null && userId != null) {
            sharedPrefsService.saveAuthToken(token)
            sharedPrefsService.saveUserData(userId, name, email)
            navController.navigate(NavigationScreens.HOME_SCREEN.name)
          } else {
            _error.value = "Erro ao salvar informações do usuário"
          }
          _isLoading.value = false
        } else {
          _error.value = "Erro ao logar: ${response.message()}"
        }
      } catch (e: Error) {
        _error.value = "Erro inesperado: ${e.localizedMessage}"
        e.printStackTrace()
      } finally {
        _isLoading.value = false
      }
    }
  }
}