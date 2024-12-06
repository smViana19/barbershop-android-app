package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiAuthRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
