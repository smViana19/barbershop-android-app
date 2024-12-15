package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiProfessionalService
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessionalsViewModel @Inject constructor(
    private val apiProfessionalService: ApiProfessionalService
) : ViewModel() {
    init {
        getAllProfessionals()
    }
    private val _professionals = mutableStateOf<List<ApiProfessionalResponse>>(emptyList())
    val professionals: MutableState<List<ApiProfessionalResponse>> = _professionals
    fun getAllProfessionals() {
        viewModelScope.launch {
            try {
                val response = apiProfessionalService.getAllProfessionals()
                _professionals.value = response
                for (res in response) {
                    println(res.user.name)
                }
                println("_professionals: $_professionals")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getProfessionalById(professionalId: Int) {

    }
}