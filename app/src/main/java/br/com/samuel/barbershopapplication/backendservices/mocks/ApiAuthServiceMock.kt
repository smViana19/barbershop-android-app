package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.api.ApiAuthService
import br.com.samuel.barbershopapplication.model.ApiAuthRequest
import br.com.samuel.barbershopapplication.model.ApiAuthResponse
import retrofit2.Response

class ApiAuthServiceMock: ApiAuthService {
    override suspend fun login(apiAuthRequest: ApiAuthRequest): Response<ApiAuthResponse> {
        TODO("Not yet implemented")
    }
}