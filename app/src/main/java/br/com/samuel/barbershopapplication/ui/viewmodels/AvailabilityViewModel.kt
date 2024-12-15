package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AvailabilityViewModel @Inject constructor(
    private val apiAvailabilityService: ApiAvailabilityService
) : ViewModel() {

    fun getAllAvailabilities() {}
    fun getAvailabilityById() {}
    fun getAvailabilitiesByProfessionalId() {}
}