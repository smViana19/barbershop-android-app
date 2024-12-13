package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiAuthRequest
import br.com.samuel.barbershopapplication.model.ApiAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAuthService {
    @POST("/login")
    suspend fun login(@Body apiAuthRequest: ApiAuthRequest): Response<ApiAuthResponse>
}