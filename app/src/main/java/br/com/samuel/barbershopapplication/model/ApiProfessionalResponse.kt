package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiProfessionalResponse (

    @SerializedName("user")
    var user: ApiUserResponse,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("specialty")
    var specialty: ApiSpecialtyResponse
)