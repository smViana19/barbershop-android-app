package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiProfessionalService {
    @GET("/professionals")
    suspend fun getAllProfessionals(): List<ApiProfessionalResponse>

    @GET("/professionals/{professionalId}")
    suspend fun getProfessionalById(@Path("professionalId") professionalId: Int): ApiProfessionalResponse
}