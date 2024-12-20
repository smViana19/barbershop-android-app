package br.com.samuel.barbershopapplication.ui.viewmodels

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.samuel.barbershopapplication.backendservices.api.ApiUserService
import br.com.samuel.barbershopapplication.model.ApiUserRequest
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiUserService: ApiUserService
) : ViewModel() {
    private val _name = mutableStateOf("")
    val name: MutableState<String> = _name
    private val _email = mutableStateOf("")
    val email: MutableState<String> = _email
    private val _password = mutableStateOf("")
    val password: MutableState<String> = _password
    private val _error = mutableStateOf("")
    val error: MutableState<String> = _error

    //TODO: AJUSTAR PARA QUE PEGUE O ESTADO AUTO NO INPUT FUTURAMENTE

    fun onClickRegisterButton(navController: NavController) {
        viewModelScope.launch {

            if(name.value.isBlank() || name.value.isEmpty()) {
                error.value = "Preencha o nome"
                return@launch
            }
            if(email.value.isBlank() || email.value.isEmpty()) {
                error.value = "Preencha o email"
                return@launch
            }
            if(password.value.isBlank() || password.value.isEmpty()) {
                error.value = "Preencha a senha"
                return@launch
            }

            val apiUserRequest = ApiUserRequest(name = _name.value, email = _email.value, password = _password.value)
            try {
                val response = apiUserService.createUser(apiUserRequest)
                when (response.code()) {
                    201 -> {
                        navController.navigate(NavigationScreens.LOGIN_SCREEN.name)
                    }
                    400 -> {
                        val errorMessage = response.errorBody()?.string()
                        print(errorMessage)
                    }
                    500 -> {
                        val errorMessage = response.errorBody()?.string()
                        print(errorMessage)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                print(e)
            }
        }

    }
    fun onClickNavigationLoginScreen(navController: NavController) {
        navController.navigate(NavigationScreens.LOGIN_SCREEN.name)
    }
}