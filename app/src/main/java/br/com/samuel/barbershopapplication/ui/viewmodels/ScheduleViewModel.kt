package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
import br.com.samuel.barbershopapplication.backendservices.api.ApiProfessionalService
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
  private val apiAvailabilityService: ApiAvailabilityService,
  private val apiProfessionalService: ApiProfessionalService

) : ViewModel() {
  private val _professionals = mutableStateOf<List<ApiProfessionalResponse>>(emptyList())
  val professionals: MutableState<List<ApiProfessionalResponse>> = _professionals

  private val _availabilities = mutableStateOf<List<ApiAvailabilityResponse>>(emptyList())
  val availabilities: MutableState<List<ApiAvailabilityResponse>> = _availabilities

  private val _selectedDate = mutableStateOf("")
  val selectedDate: MutableState<String> = _selectedDate

  val filteredAvailabilities = MutableStateFlow<List<ApiAvailabilityResponse>>(emptyList())

  fun getAllProfessionals() {
    viewModelScope.launch {
      try {
        val response = apiProfessionalService.getAllProfessionals()
        _professionals.value = response
        for (res in response) {
          println(res.user.name)
        }
        println("_professionals: $_professionals")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun getAvailabilitiesByProfessionalId(professionalId: Int) {
    viewModelScope.launch {
      try {
        val response = apiAvailabilityService.getAvailabilitiesByProfessionalId(professionalId)
        _availabilities.value = response
        filteredDate(_selectedDate.value)
        //        when (response.code()) {
        //          200 -> {
        //
        //          }
        //        }


      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun selectDate(date: String) {
    _selectedDate.value = date
    filteredDate(date)

  }

  private fun filteredDate(date: String?) {
    filteredAvailabilities.value = if (date != null) {
      _availabilities.value.filter { it.date == date }
    } else {
      _availabilities.value
    }
  }
}