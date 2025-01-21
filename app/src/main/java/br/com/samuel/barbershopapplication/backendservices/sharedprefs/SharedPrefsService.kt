package br.com.samuel.barbershopapplication.backendservices.sharedprefs

import br.com.samuel.barbershopapplication.model.ApiUserRequest

interface SharedPrefsService {
    fun saveUserData(id: Int, name: String, email: String)
    fun getUserData(): ApiUserRequest
    fun getUserId(): Int

    fun saveAuthToken(token: String)

    fun isLoggedIn(): Boolean
    fun clearAuthToken()

}