package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiServiceRequest(
    @SerializedName("specialtyId")
    val specialtyId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double //TODO: PODE SER FLOAT ISSO LEMBRAR DE CONFERIR DPS
)
