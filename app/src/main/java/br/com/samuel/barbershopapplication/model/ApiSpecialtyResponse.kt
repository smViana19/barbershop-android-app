package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiSpecialtyResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String
)
