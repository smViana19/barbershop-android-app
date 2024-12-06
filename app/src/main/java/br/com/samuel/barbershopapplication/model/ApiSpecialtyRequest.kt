package br.com.samuel.barbershopapplication.model

import com.google.gson.annotations.SerializedName

data class ApiSpecialtyRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String
)
