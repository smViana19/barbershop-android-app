package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.api.ApiServiceService
import br.com.samuel.barbershopapplication.model.ApiServiceResponse

class ApiServiceServiceMock: ApiServiceService {
    override suspend fun getAllServices(): List<ApiServiceResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getServiceById(serviceId: Int): ApiServiceResponse {
        TODO("Not yet implemented")
    }
}