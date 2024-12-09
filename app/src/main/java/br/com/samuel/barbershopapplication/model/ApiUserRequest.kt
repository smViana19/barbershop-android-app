package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiUserRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
//    @SerializedName("role")
//    var role: String?
)
