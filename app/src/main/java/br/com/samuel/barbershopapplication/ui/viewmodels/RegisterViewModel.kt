package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.samuel.barbershopapplication.backendservices.api.ApiUserService
import br.com.samuel.barbershopapplication.model.ApiUserRequest
import br.com.samuel.barbershopapplication.ui.dialogs.DialogState
import br.com.samuel.barbershopapplication.ui.dialogs.DialogType
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> = _isLoading
    private val _dialogState = mutableStateOf(DialogState(open = false))
    val dialogState: State<DialogState> = _dialogState

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
                _isLoading.value = true
                val response = apiUserService.createUser(apiUserRequest)
                when (response.code()) {
                    201 -> {
                        _isLoading.value = false
                        _dialogState.value = DialogState(
                            open = true,
                            type = DialogType.SUCCESS,
                            title = "Registrado com sucesso",
                            onConfirm = {
                                onClickButtonDismissDialog()
                            }
                        )
                        navController.navigate(NavigationScreens.LOGIN_SCREEN.name)
                    }
                    400 -> {
                        val errorMessage = response.errorBody()?.string()
                        _isLoading.value = false
                        _dialogState.value = DialogState(
                            open = true,
                            type = DialogType.ERROR,
                            title = "Alerta",
                            msg = errorMessage,
                            onConfirm = {
                                onClickButtonDismissDialog()
                            }
                        )
                    }
                    500 -> {
                        val errorBody = response.errorBody()?.string()
                        _isLoading.value = true
                        _dialogState.value = DialogState(
                            open = true,
                            type = DialogType.ERROR,
                            title = "Erro ao criar usuário",
                            msg = "Tente novamente",
                            onConfirm = {
                                onClickButtonDismissDialog()
                            }
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                println(e)
            }
            finally {
              _isLoading.value = false
            }
        }

    }
    fun onClickNavigationLoginScreen(navController: NavController) {
        navController.navigate(NavigationScreens.LOGIN_SCREEN.name)
    }

    private fun onClickButtonDismissDialog() {
        _dialogState.value = DialogState(
            open = false
        )
    }
}
