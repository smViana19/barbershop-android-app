package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiServiceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceService {
    @GET("/services")
    suspend fun getAllServices(): List<ApiServiceResponse>

    @GET("/services/{serviceId}")
    suspend fun getServiceById(@Path("serviceId") serviceId: Int): List<ApiServiceResponse>
}