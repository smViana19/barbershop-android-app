package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiAvailabilityRequest(
    @SerializedName("professionalId")
    var professionalId: Int,
    @SerializedName("date")
    var date: String, //LocalDate
    @SerializedName("time")
    var time: String, //LocalTime
    @SerializedName("isAvailable")
    var isAvailable: Boolean
)
