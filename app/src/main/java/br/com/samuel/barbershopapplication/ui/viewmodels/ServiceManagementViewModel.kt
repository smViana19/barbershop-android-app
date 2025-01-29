package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.samuel.barbershopapplication.backendservices.api.ApiProfessionalService
import br.com.samuel.barbershopapplication.backendservices.api.ApiServiceService
import br.com.samuel.barbershopapplication.backendservices.api.ApiSpecialtyService
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import br.com.samuel.barbershopapplication.model.ApiServiceResponse
import br.com.samuel.barbershopapplication.model.ApiSpecialtyResponse
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceManagementViewModel @Inject constructor(
  private val sharedPrefsService: SharedPrefsService,
  private val apiProfessionalService: ApiProfessionalService,
  private val apiServiceService: ApiServiceService,
  private val apiSpecialtyService: ApiSpecialtyService
) : ViewModel() {

  private val _isLoggedIn = mutableStateOf(false)
  val isLoggedIn: MutableState<Boolean> = _isLoggedIn

  private val _professionals = mutableStateOf<List<ApiProfessionalResponse>>(emptyList())
  val professionals: MutableState<List<ApiProfessionalResponse>> = _professionals

  private val _serviceData = mutableStateOf<List<ApiServiceResponse>>(emptyList())
  val serviceData: MutableState<List<ApiServiceResponse>> = _serviceData

  private val _specialties = mutableStateOf<List<ApiSpecialtyResponse>>(emptyList())
  val specialties: MutableState<List<ApiSpecialtyResponse>> = _specialties

  private val _isLoading = mutableStateOf(false)
  val isLoading: MutableState<Boolean> = _isLoading

  private val _filteredService = MutableStateFlow<List<ApiServiceResponse>>(emptyList())
  val filteredService: StateFlow<List<ApiServiceResponse>> = _filteredService

  private val _filteredProfessional = MutableStateFlow<List<ApiProfessionalResponse>>(emptyList())
  val filteredProfessional: StateFlow<List<ApiProfessionalResponse>> = _filteredProfessional

  private val _filteredSpecialties = MutableStateFlow<List<ApiSpecialtyResponse>>(emptyList())
  val filteredSpecialties: StateFlow<List<ApiSpecialtyResponse>> = _filteredSpecialties


  fun getAllServices() {
    viewModelScope.launch {
      try {
        _isLoading.value = true
        val response = apiServiceService.getAllServices()
        _serviceData.value = response
        _filteredService.value = response
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        _isLoading.value = false
      }
    }
  }

  fun getAllProfessionals() {
    viewModelScope.launch {
      try {
        _isLoading.value = true
        val response = apiProfessionalService.getAllProfessionals()
        _professionals.value = response
        _filteredProfessional.value = response
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        _isLoading.value = false
      }
    }
  }

  fun getAllSpecialties() {
    viewModelScope.launch {
      try {
        _isLoading.value = true
        val response = apiSpecialtyService.getAllSpecialties()
        _specialties.value = response
        _filteredSpecialties.value = response
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        _isLoading.value = false
      }
    }
  }

  fun verifyIsUserLoggedIn(navController: NavController) {
    _isLoading.value = true
    val token = sharedPrefsService.isLoggedIn()
    if (!token) {
      navController.navigate(NavigationScreens.LOGIN_SCREEN.name)
    } else {
      _isLoggedIn.value = true
      _isLoading.value = false
    }
    _isLoading.value = false
  }

  fun filterService(text: String) {
    _filteredService.value = if (text.isEmpty()) {
      _serviceData.value
    } else {
      _serviceData.value.filter {
        it.name.contains(text, ignoreCase = true)
      }
    }
  }

  fun filterProfessional(text: String) {
    _filteredProfessional.value = if (text.isEmpty()) {
      _professionals.value
    } else {
      _professionals.value.filter {
        it.user.name.contains(text, ignoreCase = true)
      }
    }
  }

  fun filterSpecialty(text: String) {
    _filteredSpecialties.value = if (text.isNotEmpty()) {
      _specialties.value.filter {
        it.name.contains(text, ignoreCase = true)
      }
    } else {
      _specialties.value
    }
  }

}