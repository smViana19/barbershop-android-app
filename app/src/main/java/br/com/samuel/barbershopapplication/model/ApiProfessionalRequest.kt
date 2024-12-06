package br.com.samuel.barbershopapplication.model

data class ApiProfessionalRequest(
    val id: Int,
    var userId: Int,
    var specialtyId: Int
)
