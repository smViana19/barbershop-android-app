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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val sharedPrefsService: SharedPrefsService,
  private val apiProfessionalService: ApiProfessionalService,
  private val apiServiceService: ApiServiceService,
  private val apiSpecialtyService: ApiSpecialtyService
) : ViewModel() {

  private val _professionals = mutableStateOf<List<ApiProfessionalResponse>>(emptyList())
  val professionals: MutableState<List<ApiProfessionalResponse>> = _professionals
  private val _serviceData = mutableStateOf<List<ApiServiceResponse>>(emptyList())
  val serviceData: MutableState<List<ApiServiceResponse>> = _serviceData
  private val _specialties = mutableStateOf<List<ApiSpecialtyResponse>>(emptyList())
  val specialties: MutableState<List<ApiSpecialtyResponse>> = _specialties


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

  fun verifyIsUserLoggedIn(navController: NavController) {
    if(!sharedPrefsService.isLoggedIn()) {
      navController.navigate(NavigationScreens.LOGIN_SCREEN.name)
    }
  }
}