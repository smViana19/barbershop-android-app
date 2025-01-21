package br.com.samuel.barbershopapplication.backendservices.sharedprefs

import android.content.Context
import br.com.samuel.barbershopapplication.model.ApiUserRequest

class SharedPrefsServiceImpl(context: Context) : SharedPrefsService{
    private val tokenPreferences = context.getSharedPreferences("token_prefs", Context.MODE_PRIVATE)
    private val userPreferences = context.getSharedPreferences("user_data_prefs", Context.MODE_PRIVATE)
    override fun saveUserData(
        id: Int,
        name: String,
        email: String,
    ) {
        userPreferences.edit().apply {
            putInt("user_id", id)
            putString("user_name", name)
            putString("user_email", email)
            apply()
        }
    }

    override fun getUserData(): ApiUserRequest {

        val name = tokenPreferences.getString("user_name", "") ?: ""
        val email = tokenPreferences.getString("user_email", "") ?: ""
        val password = tokenPreferences.getString("user_password", "") ?: ""
        val role = tokenPreferences.getString("user_role", "") ?: ""
        return ApiUserRequest(name, email, password)
    }

    override fun getUserId(): Int {
        val id = userPreferences.getInt("user_id", 0)
        return id
    }

    override fun saveAuthToken(token: String) {
        tokenPreferences.edit().apply {
            putString("auth_token", token)
            apply()
        }
    }

    override fun isLoggedIn(): Boolean {
        val token = tokenPreferences.getString("auth_token", null)
        return !token.isNullOrEmpty()
    }

    override fun clearAuthToken() {
        tokenPreferences.edit().apply {
            remove("auth_token")
            apply()
        }
    }


}