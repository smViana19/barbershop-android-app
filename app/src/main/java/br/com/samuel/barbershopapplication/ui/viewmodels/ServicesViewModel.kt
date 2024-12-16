package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.barbershopapplication.backendservices.api.ApiServiceService
import br.com.samuel.barbershopapplication.model.ApiServiceResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val apiServiceService: ApiServiceService
) : ViewModel() {

    private val _serviceData = mutableStateOf<List<ApiServiceResponse>>(emptyList())
    val serviceData: MutableState<List<ApiServiceResponse>> = _serviceData
    fun getAllServices() {
        viewModelScope.launch {
            try {
                val response = apiServiceService.getAllServices()
                _serviceData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}