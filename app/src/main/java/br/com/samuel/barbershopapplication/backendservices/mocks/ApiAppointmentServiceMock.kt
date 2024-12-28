package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.api.ApiAppointmentService
import br.com.samuel.barbershopapplication.model.ApiAppointmentRequest
import br.com.samuel.barbershopapplication.model.ApiAppointmentResponse

class ApiAppointmentServiceMock: ApiAppointmentService {
  override suspend fun getAllAppointments(): List<ApiAppointmentResponse> {
    TODO("Not yet implemented")
  }

  override suspend fun getAppointmentById(appointmentId: Int): ApiAppointmentResponse {
    TODO("Not yet implemented")
  }

  override suspend fun createAppointment(apiAppointmentRequest: ApiAppointmentRequest): ApiAppointmentResponse {
    TODO("Not yet implemented")
  }

  override suspend fun getAppointmentByUserId(userId: Int): List<ApiAppointmentResponse> {
    TODO("Not yet implemented")
  }
}