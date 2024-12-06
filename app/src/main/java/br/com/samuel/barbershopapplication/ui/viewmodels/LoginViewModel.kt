package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.samuel.barbershopapplication.backendservices.api.ApiAuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiAuthService: ApiAuthService,
): ViewModel() {

}