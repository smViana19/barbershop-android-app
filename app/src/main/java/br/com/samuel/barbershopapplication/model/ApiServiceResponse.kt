package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiServiceResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("specialtyId")
    var specialtyId: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: Double
)