package br.com.samuel.barbershopapplication.backendservices.sharedprefs

import android.content.Context
import br.com.samuel.barbershopapplication.model.ApiUserRequest

class SharedPrefsServiceImpl(context: Context) : SharedPrefsService{
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    override fun saveUserData(
        name: String,
        email: String,
        password: String,
        role: String
    ) {
        TODO("Not yet implemented")
    }

    override fun getUserData(): ApiUserRequest {
        TODO("Not yet implemented")
    }
}