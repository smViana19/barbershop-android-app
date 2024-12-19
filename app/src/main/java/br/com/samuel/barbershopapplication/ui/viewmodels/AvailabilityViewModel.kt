  package br.com.samuel.barbershopapplication.ui.viewmodels

  import android.R
  import androidx.compose.runtime.MutableState
  import androidx.compose.runtime.mutableStateOf
  import androidx.lifecycle.ViewModel
  import androidx.lifecycle.viewModelScope
  import br.com.samuel.barbershopapplication.backendservices.api.ApiAvailabilityService
  import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
  import dagger.hilt.android.lifecycle.HiltViewModel
  import kotlinx.coroutines.flow.MutableStateFlow
  import kotlinx.coroutines.launch
  import retrofit2.Response
  import javax.inject.Inject

  @HiltViewModel
  class AvailabilityViewModel @Inject constructor(
    private val apiAvailabilityService: ApiAvailabilityService
  ) : ViewModel() {
    private val _availabilities = mutableStateOf <List<ApiAvailabilityResponse>>(emptyList())
    val availabilities: MutableState<List<ApiAvailabilityResponse>> = _availabilities

    private val _selectedDate = mutableStateOf("")
    val selectedDate: MutableState<String> = _selectedDate

    val filteredAvailabilities = MutableStateFlow<List<ApiAvailabilityResponse>>(emptyList())


  //  fun getAvailabilityById(availabilityId: Int) {
  //    viewModelScope.launch {
  //      try {
  //        val response = apiAvailabilityService.getAvailabilityById(availabilityId)
  //        _availabilities.value = response
  //
  //      } catch (e: Exception) {
  //        e.printStackTrace()
  //      }
  //    }
  //  }

    fun getAvailabilitiesByProfessionalId(professionalId: Int) {
      viewModelScope.launch {
        try {
          val response = apiAvailabilityService.getAvailabilitiesByProfessionalId(professionalId)
              _availabilities.value = response
          filteredDate(_selectedDate.value)
  //        when (response.code()) {
  //          200 -> {
  //
  //          }
  //        }


        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }

    fun selectDate(date: String) {
      _selectedDate.value = date
      filteredDate(date)

    }
    private fun filteredDate(date: String?) {
      filteredAvailabilities.value = if(date != null) {
        _availabilities.value.filter { it.date == date }
      } else {
        _availabilities.value
      }
    }
  }