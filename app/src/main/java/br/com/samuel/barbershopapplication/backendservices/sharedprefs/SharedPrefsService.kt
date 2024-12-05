package br.com.samuel.barbershopapplication.backendservices.sharedprefs

import br.com.samuel.barbershopapplication.model.ApiUserRequest

interface SharedPrefsService {
    fun saveUserData(name: String, email: String, password: String, role: String)
    fun getUserData(): ApiUserRequest
}