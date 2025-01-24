package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.samuel.barbershopapplication.ui.theme.AppTheme

@Composable
fun AppointmentScreen() {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    Row {
      Text(text = "Agendamentos")
    }

  }
}

@Preview(showBackground = true)
@Composable
private fun AppointmentScreenPreview() {
  AppTheme {
    AppointmentScreen()
  }
}