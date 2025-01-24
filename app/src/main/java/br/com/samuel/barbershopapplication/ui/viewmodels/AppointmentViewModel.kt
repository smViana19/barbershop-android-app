package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiAppointmentService
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.model.ApiAppointmentResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
  private val apiAppointmentService: ApiAppointmentService,
  private val sharedPrefsService: SharedPrefsService
): ViewModel() {
  private val _userId = sharedPrefsService.getUserId()
  private val _appointments = mutableStateOf<List<ApiAppointmentResponse>>(emptyList())
  val appointments: MutableState<List<ApiAppointmentResponse>> = _appointments

  fun getPendingUserAppointments() {
    viewModelScope.launch {
      try {
        val appointments = apiAppointmentService.getAppointmentByUserId(_userId)
        val pendingAppointments = appointments.filter { it.status == AppointmentStatus.PENDING.status }
        _appointments.value = pendingAppointments
        println("appointment value: ${_appointments.value}")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
}

enum class AppointmentStatus(val status: String) {
  PENDING("PENDING"),
  COMPLETED("COMPLETED"),
  CANCELED("CANCELED") //PARAMETER DISABLED
}