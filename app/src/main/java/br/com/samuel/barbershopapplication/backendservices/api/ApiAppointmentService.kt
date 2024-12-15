package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiAppointmentRequest
import br.com.samuel.barbershopapplication.model.ApiAppointmentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiAppointmentService {
    @GET("/appointments")
    suspend fun getAllAppointments(): List<ApiAppointmentResponse>
    @GET("/appointments/{appointmentId}")
    suspend fun getAppointmentById(@Path("appointmentId") appointmentId: Int): ApiAppointmentResponse
    @POST("/appointments")
    suspend fun createAppointment(@Body apiAppointmentRequest: ApiAppointmentRequest): ApiAppointmentResponse
    @GET("/appointments/user/{userId}")
    suspend fun getAppointmentByUserId(@Path("userId") userId: Int): List<ApiAppointmentResponse>
    //TODO: IMPLEMENT METHOD UPDATE APPOINTMENT
}