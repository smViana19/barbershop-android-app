package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiSpecialtyService
import br.com.samuel.barbershopapplication.model.ApiSpecialtyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecialtyViewModel @Inject constructor(
    private val apiSpecialtyService: ApiSpecialtyService
) : ViewModel() {

    private val _specialties = mutableStateOf<List<ApiSpecialtyResponse>>(emptyList())
    val specialties: MutableState<List<ApiSpecialtyResponse>> = _specialties
    fun getAllSpecialties() {
        viewModelScope.launch {
            try {
                val response = apiSpecialtyService.getAllSpecialties()
                _specialties.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getSpecialtyById() {}
}