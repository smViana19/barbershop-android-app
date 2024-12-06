package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.samuel.barbershopapplication.backendservices.api.ApiUserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiUserService: ApiUserService
) : ViewModel() {

}