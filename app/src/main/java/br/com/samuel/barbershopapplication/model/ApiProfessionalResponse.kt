package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiProfessionalResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    var user: ApiUserResponse,
    @SerializedName("specialty")
    var specialty: ApiSpecialtyResponse
)