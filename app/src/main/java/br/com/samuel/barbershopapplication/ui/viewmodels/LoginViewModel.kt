package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiAuthService
import br.com.samuel.barbershopapplication.model.ApiAuthRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiAuthService: ApiAuthService,
): ViewModel() {
    private val _email = mutableStateOf("")
    val email: MutableState<String> = _email
    private val _password = mutableStateOf("")
    val password: MutableState<String> = _password

    fun onClickButtonLogin() {
        viewModelScope.launch {
            val apiAuthRequest = ApiAuthRequest(_email.value, _password.value)
            try {
                // TODO: Login request/response api 
                val response = apiAuthService.login(apiAuthRequest)
                if(response.isSuccessful) {
                    println("Logado com sucesso")
                } else {
                    println("Erro ao logar")
                }

            } catch (e: Error) {
                e.printStackTrace()
            }
        }
    }
}