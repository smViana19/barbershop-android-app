package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.samuel.barbershopapplication.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiUserServiceMock
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.RegisterViewModel

@Composable
fun RegisterScreen(
  registerViewModel: RegisterViewModel = hiltViewModel(),
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
        .fillMaxSize()
        .padding(16.dp)
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
          text = "Criar uma conta",
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
          value = registerViewModel.name.value,
          onValueChange = { newValue ->
            registerViewModel.name.value = newValue
          },
          shape = RoundedCornerShape(8.dp),
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
          ),
          label = { Text("Nome completo") }
        )
        OutlinedTextField(
          value = registerViewModel.email.value,
          onValueChange = { newValue ->
            registerViewModel.email.value = newValue
          },
          shape = RoundedCornerShape(8.dp),
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next

          ),
          label = { Text("Email") }
        )
        OutlinedTextField(
          value = registerViewModel.password.value,
          onValueChange = { newValue ->
            registerViewModel.password.value = newValue
          },
          shape = RoundedCornerShape(8.dp),
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth(),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
          ),
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
          label = { Text("Senha") }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = registerViewModel.error.value,
            color = Color(0xFFFF0000)
          )
        }
        Button(
          modifier = Modifier
            .fillMaxWidth(),
          onClick = {
            registerViewModel.onClickRegisterButton(navController)
          },
          shape = RoundedCornerShape(8.dp),
        ) {
          Text(text = "Cadastrar")
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
                registerViewModel.onClickNavigationLoginScreen(navController)
              },
            text = "Entrar",
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp
          )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(text = "Política de Privacidade", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
        }
      }
    }
  }

}
@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
  val apiUserServiceMock = ApiUserServiceMock()
  val registerViewModel = RegisterViewModel(apiUserServiceMock)
  val navController = rememberNavController()
  BarbershopApplicationTheme {
    RegisterScreen(registerViewModel, navController)
  }
}