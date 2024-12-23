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
        sharedPreferences.edit().apply {
            putString("user_name", name)
            putString("user_email", email)
            putString("user_password", password)
            putString("user_role", role)
            apply()
        }
    }

    override fun getUserData(): ApiUserRequest {
        val name = sharedPreferences.getString("user_name", "") ?: ""
        val email = sharedPreferences.getString("user_email", "") ?: ""
        val password = sharedPreferences.getString("user_password", "") ?: ""
        val role = sharedPreferences.getString("user_role", "") ?: ""
        return ApiUserRequest(name, email, password)
    }

    override fun saveAuthToken(token: String) {
        sharedPreferences.edit().apply {
            putString("auth_token", token)
            apply()
        }
    }

    override fun isLoggedIn(): Boolean {
        val token = sharedPreferences.getString("auth_token", null)
        return !token.isNullOrEmpty()
    }

    override fun clearAuthToken() {
        sharedPreferences.edit().apply {
            remove("auth_token")
            apply()
        }
    }


}