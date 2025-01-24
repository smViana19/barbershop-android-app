package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiAppointmentService
import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
import br.com.samuel.barbershopapplication.backendservices.api.ApiProfessionalService
import br.com.samuel.barbershopapplication.backendservices.api.ApiServiceService
import br.com.samuel.barbershopapplication.model.ApiAppointmentRequest
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
  private val apiAvailabilityService: ApiAvailabilityService,
  private val apiProfessionalService: ApiProfessionalService,
  private val apiAppointmentService: ApiAppointmentService,
  private val apiServiceService: ApiServiceService

) : ViewModel() {

  private val _serviceName = mutableStateOf("")
  val serviceName: MutableState<String> = _serviceName

  private val _professionalName = mutableStateOf("")
  val professionalName: MutableState<String> = _professionalName

  private val _servicePrice = mutableDoubleStateOf(0.0)
  val servicePrice: MutableState<Double> = _servicePrice

  private val _details = mutableStateOf("")
  val details: MutableState<String> = _details

  private val _professionals = mutableStateOf<List<ApiProfessionalResponse>>(emptyList())
  val professionals: MutableState<List<ApiProfessionalResponse>> = _professionals

  private val _availabilityTime = mutableStateOf("")
  val availabilityTime: MutableState<String> = _availabilityTime

  val filteredAvailabilities = MutableStateFlow<List<ApiAvailabilityResponse>>(emptyList())
  private val _availabilities = mutableStateOf<List<ApiAvailabilityResponse>>(emptyList())
  private val _selectedDate = mutableStateOf("")

  fun createAppointment(
    userId: Int,
    professionalId: Int,
    serviceId: Int,
    availabilityId: Int,
    details: String?
  ) {
    viewModelScope.launch {
      try {
        val apiAppointmentRequest = ApiAppointmentRequest(
          userId,
          professionalId,
          serviceId,
          availabilityId,
          details
        )
        apiAppointmentService.createAppointment(apiAppointmentRequest)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun getServiceById(serviceId: Int) {
    viewModelScope.launch {
      try {
        val service = apiServiceService.getServiceById(serviceId)
        _serviceName.value = service.name
        _servicePrice.doubleValue = service.price
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }


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

  fun getProfessionalById(professionalId: Int) {
    viewModelScope.launch {
      try {
        val professional = apiProfessionalService.getProfessionalById(professionalId)
        _professionalName.value = professional.user.name
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun getAvailabilityById(availabilityId: Int) {
    viewModelScope.launch {
      try {
        val availability = apiAvailabilityService.getAvailabilityById(availabilityId)
        _availabilityTime.value = availability.time
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
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun selectDate(date: String) {
    _selectedDate.value = date
    filteredDate(date)

  }

  fun filteredDate(date: String?) {
    filteredAvailabilities.value = if (date != null) {
      _availabilities.value.filter { it.date == date }
    } else {
      _availabilities.value
    }
  }


}