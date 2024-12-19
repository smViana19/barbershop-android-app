package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AvailabilityViewModel @Inject constructor(
  private val apiAvailabilityService: ApiAvailabilityService
) : ViewModel() {
  private val _availabilities = mutableStateOf <List<ApiAvailabilityResponse>>(emptyList())
  val availabilities: MutableState<List<ApiAvailabilityResponse>> = _availabilities

//  fun getAllAvailabilities() {
//    viewModelScope.launch {
//      try {
//        val response = apiAvailabilityService.getAllAvailabilites()
//        _availabilities.value = response
//        for (res in response) {
//          println("getAllAvailabilities res: " + res.date + res.time)
//        }
//      } catch (e: Exception) {
//        e.printStackTrace()
//      }
//    }
//
//  }
//
//  fun getAvailabilityById(availabilityId: Int) {
//    viewModelScope.launch {
//      try {
//        val response = apiAvailabilityService.getAvailabilityById(availabilityId)
//        _availabilities.value = response
//
//      } catch (e: Exception) {
//        e.printStackTrace()
//      }
//    }
//  }

  fun getAvailabilitiesByProfessionalId(professionalId: Int) {
    viewModelScope.launch {
      try {
        val response = apiAvailabilityService.getAvailabilitiesByProfessionalId(professionalId)
            _availabilities.value = response
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
}