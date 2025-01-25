package br.com.samuel.barbershopapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val sharedPrefsService: SharedPrefsService,
) : ViewModel() {

}