package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiAvailabilityResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("professionalId")
    var professionalId: Int,
    @SerializedName("date")
    var date: String, //LocalDate
    @SerializedName("time")
    var time: String, // LocalTime
    @SerializedName("isAvailable")
    var isAvailable: Boolean
)
