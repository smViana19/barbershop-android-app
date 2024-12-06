package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiSpecialtyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiSpecialtyService {
    @GET("/specialties")
    suspend fun getAllSpecialties(): List<ApiSpecialtyResponse>

    @GET("/specialties/{specialtyId}")
    suspend fun getSpecialtyById(@Path("specialtyId") specialtyId: Int): List<ApiSpecialtyResponse>

}