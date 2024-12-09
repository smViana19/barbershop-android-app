package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    
}