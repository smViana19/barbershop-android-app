package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.components.AppButton
import br.com.samuel.barbershopapplication.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
  val focusManager = LocalFocusManager.current

  Scaffold(
    modifier = Modifier.pointerInput(Unit) {
      detectTapGestures {
        focusManager.clearFocus()
      }
    },
    topBar = {
      TopAppBar(
        title = {
          Text(text = "Meu perfil")
        },
        navigationIcon = {
          IconButton(onClick = {}) {
            Icon(Icons.Filled.ArrowBack, "backIcon")
          }
        },
        colors = topAppBarColors(
          containerColor = Color(0xFF0F172A),
          scrolledContainerColor = MaterialTheme.colorScheme.primary,
          navigationIconContentColor = Color.White,
          titleContentColor = Color.White,
          actionIconContentColor = Color.White
        ),
      )
    }, content = {
      Column(
        modifier = Modifier
          .padding(it)
          .fillMaxSize()
          .background(Color(0xffF0F4F8)),
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp),
        ) {
          Text("Minha foto")
        }
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          //TODO: PEGAR FOTO DO USUARIO
          Box(
            modifier = Modifier
              .size(100.dp)
              .clip(CircleShape)
              .background(Color.Gray)
          )
          Spacer(modifier = Modifier.padding(8.dp))
          AppButton(
            modifier = Modifier,
            onClick = {},
            text = "Alterar foto",
            trailingIcon = {
              Icon(painter = painterResource(R.drawable.ic_camera_24), contentDescription = "")
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color.White,
              contentColor = MaterialTheme.colorScheme.primary
            ),
            textColor = MaterialTheme.colorScheme.primary,
            iconSpacing = 10.dp,
            border = BorderStroke(1.dp, color = Color(0xffE1E7EF)),
            shape = RoundedCornerShape(8.dp),
          )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = Color.White,
              unfocusedContainerColor = Color.White,
              disabledContainerColor = Color.White
            ),
            label = {
              Text("Nome")
            },
            placeholder = {
              Text("Digite o nome")
            }
          )
          Spacer(modifier = Modifier.height(16.dp))
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = Color.White,
              unfocusedContainerColor = Color.White,
              disabledContainerColor = Color.White,
            ),
            label = {
              Text("Email") // Cor do texto do label
            },
            placeholder = {
              Text("Digite o email")
            }
          )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
          AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Salvar informações",
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF10B880),
              contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
          )
          AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Sair da conta",
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFEE4444),
              contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
          )
        }

      }
    })
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
  AppTheme {
    ProfileScreen()
  }
}