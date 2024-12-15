package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.api.ApiSpecialtyService
import br.com.samuel.barbershopapplication.model.ApiSpecialtyResponse

class ApiSpecialtyServiceMock: ApiSpecialtyService {
    override suspend fun getAllSpecialties(): List<ApiSpecialtyResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpecialtyById(specialtyId: Int): List<ApiSpecialtyResponse> {
        TODO("Not yet implemented")
    }
}