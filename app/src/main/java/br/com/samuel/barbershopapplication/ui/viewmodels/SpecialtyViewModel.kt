package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.samuel.barbershopapplication.backendservices.api.ApiSpecialtyService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtyViewModel @Inject constructor(
    private val apiSpecialtyService: ApiSpecialtyService
): ViewModel(){
    fun getAllSpecialties() {}
    fun getSpecialtyById() {}
}