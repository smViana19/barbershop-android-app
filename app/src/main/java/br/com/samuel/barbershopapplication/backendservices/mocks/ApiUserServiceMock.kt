package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.api.ApiUserService
import br.com.samuel.barbershopapplication.model.ApiUserRequest
import br.com.samuel.barbershopapplication.model.ApiUserResponse
import retrofit2.Response

class ApiUserServiceMock: ApiUserService {
    override suspend fun createUser(apiUserRequest: ApiUserRequest): Response<ApiUserResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<ApiUserResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(userId: Int): ApiUserResponse {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(
        userId: Int,
        apiUserRequest: ApiUserRequest
    ): ApiUserResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: Int): ApiUserResponse {
        TODO("Not yet implemented")
    }
}