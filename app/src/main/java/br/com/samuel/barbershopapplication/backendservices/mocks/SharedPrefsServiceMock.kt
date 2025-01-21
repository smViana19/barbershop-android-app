package br.com.samuel.barbershopapplication.backendservices.mocks

import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.model.ApiUserRequest

class SharedPrefsServiceMock: SharedPrefsService {
  override fun saveUserData(
    id: Int,
    name: String,
    email: String,
  ) {
    TODO("Not yet implemented")
  }

  override fun getUserData(): ApiUserRequest {
    TODO("Not yet implemented")
  }

  override fun getUserId(): Int {
    TODO("Not yet implemented")
  }

  override fun saveAuthToken(token: String) {
    TODO("Not yet implemented")
  }

  override fun isLoggedIn(): Boolean {
    TODO("Not yet implemented")
  }

  override fun clearAuthToken() {
    TODO("Not yet implemented")
  }
}