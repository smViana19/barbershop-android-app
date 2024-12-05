package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiUserResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/users/")
    suspend fun createUser(): ApiUserResponse
    @GET("/users")
    suspend fun getAllUsers(): List<ApiUserResponse>

}