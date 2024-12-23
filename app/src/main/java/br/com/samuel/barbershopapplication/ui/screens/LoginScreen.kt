package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.samuel.barbershopapplication.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiAuthServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.SharedPrefsServiceMock
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
  loginViewModel: LoginViewModel = hiltViewModel(),
  navController: NavController
) {
  var isPasswordVisible by remember { mutableStateOf(false) }
  val focusManager = LocalFocusManager.current
  Box(
    modifier = Modifier
      .fillMaxSize()
      .statusBarsPadding()
      .background(MaterialTheme.colorScheme.background)
      .pointerInput(Unit) {
        detectTapGestures(onTap = {
          focusManager.clearFocus()
        })
      }
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .pointerInput(Unit) {
          detectTapGestures(onTap = {
            focusManager.clearFocus()
          })
        },
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        horizontalArrangement = Arrangement.Center
      ) {
        Text(
          text = "Faça login",
          fontSize = 28.sp,
          color = MaterialTheme.colorScheme.primary,
          style = MaterialTheme.typography.titleMedium,
          modifier = Modifier.padding(top = 16.dp)
        )
      }
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        verticalArrangement = Arrangement.Center
      ) {
        OutlinedTextField(
          value = loginViewModel.email.value,
          onValueChange = { newValue ->
            loginViewModel.email.value = newValue
          },
          shape = RoundedCornerShape(8.dp),
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
          ),
          label = { Text(text = "Email") }
        )
        OutlinedTextField(
          value = loginViewModel.password.value,
          onValueChange = { newValue ->
            loginViewModel.password.value = newValue
          },
          shape = RoundedCornerShape(8.dp),
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
              val image = if (isPasswordVisible) {
                R.drawable.ic_visibility_off_24
              } else {
                R.drawable.ic_visibility_on_24
              }
              Icon(
                painter = painterResource(image),
                contentDescription = if (isPasswordVisible) "Ocultar senha" else "Mostrar senha"
              )
            }
          },
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
          ),
          label = { Text(text = "Senha") }
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Button(
          modifier = Modifier.fillMaxWidth(),
          onClick = { loginViewModel.onClickButtonLogin(navController = navController) },
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(text = "Login")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "Já possui cadastro?",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp
          )
          Text(
            modifier = Modifier
              .padding(start = 4.dp)
              .clickable {
                navController.navigate(NavigationScreens.REGISTER_SCREEN.name)              },
            text = "Registrar",
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp
          )
        }
      }

    }
  }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
  val apiAuthServiceMock = ApiAuthServiceMock()
  val sharedPrefsServiceMock = SharedPrefsServiceMock()
  val loginViewModel = LoginViewModel(apiAuthServiceMock, sharedPrefsServiceMock)
  val navController = rememberNavController()
  BarbershopApplicationTheme {
    LoginScreen(loginViewModel, navController)
  }

}