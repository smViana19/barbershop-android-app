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

  fun onClickButtonLogin(navController: NavController) {
    viewModelScope.launch {
      val apiAuthRequest = ApiAuthRequest(_email.value, _password.value)
      try {
        val response = apiAuthService.login(apiAuthRequest)
        if (response.isSuccessful) {
          val token = response.body()?.token
          if (token != null) {
            sharedPrefsService.saveAuthToken(token)
          }
          navController.navigate(NavigationScreens.HOME_SCREEN.name)
        } else {
          println("Erro ao logar")
        }
      } catch (e: Error) {
        e.printStackTrace()
      }
    }
  }
}