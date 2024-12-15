package br.com.samuel.barbershopapplication.model

data class ApiAppointmentRequest (
    val userId: Int,
    val professionalId: Int,
    val serviceId: Int,
    val availabilityId: Int,
    val status: String,
)