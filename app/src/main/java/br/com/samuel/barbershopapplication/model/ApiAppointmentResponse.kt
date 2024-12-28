package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiAppointmentResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("professionalId")
    val professionalId: Int,
    @SerializedName("ServiceId")
    val serviceId: Int,
    @SerializedName("availabilityId")
    val availabilityId: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("availability")
    val availability: ApiAvailabilityResponse,
    @SerializedName("service")
    val service: ApiServiceResponse,
    @SerializedName("professional")
    val professional: ApiProfessionalResponse,
    @SerializedName("details")
    val details: String?,
    // TODO: LEMBRAR DE RESOLVER O STATUS DA REQUEST COM O STATUS DO AGENDAMENTO
)