package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import retrofit2.Response

class ApiAvailabilityServiceMock: ApiAvailabilityService {
  override suspend fun getAllAvailabilites(): Response<List<ApiAvailabilityResponse>> {
    TODO("Not yet implemented")
  }

  override suspend fun getAvailabilityById(availabilityId: Int): ApiAvailabilityResponse {
    TODO("Not yet implemented")
  }

  override suspend fun getAvailabilitiesByProfessionalId(professionalId: Int): List<ApiAvailabilityResponse> {
    TODO("Not yet implemented")
  }
}