package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiUserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("role")
    var role: String,
)