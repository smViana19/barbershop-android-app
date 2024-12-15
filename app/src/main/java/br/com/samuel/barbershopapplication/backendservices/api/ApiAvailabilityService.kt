package br.com.samuel.barbershopapplication.backendservices.api

import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiAvailabilityService {

    @GET("/availabilities")
    suspend fun getAllAvailabilites(): List<ApiAvailabilityResponse>

    @GET("/availabilities/{availabilityId}")
    suspend fun getAvailabilityById(): List<ApiAvailabilityResponse>

    @GET("/availabilities/professional/{professionalId}")
    suspend fun getAvailabilitiesByProfessionalId(@Path("professionalId") professionalId: Int): List<ApiAvailabilityResponse>

    //TODO: ADICIONAR OS OUTROS METODOS DEPOIS QUE TERMINAR NO BACK END CORRETAMENTE
}