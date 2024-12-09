package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiUserRequest
import br.com.samuel.barbershopapplication.model.ApiUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiUserService {
    @POST("/users/")
    suspend fun createUser(@Body apiUserRequest: ApiUserRequest): Response<ApiUserResponse>

    @GET("/users")
    suspend fun getAllUsers(): List<ApiUserResponse>

    @GET("/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): ApiUserResponse

    @PUT("/users/{userId}")
    suspend fun updateUser(@Path("userId") userId: Int, @Body apiUserRequest: ApiUserRequest): ApiUserResponse

    @DELETE("/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Int): ApiUserResponse

}