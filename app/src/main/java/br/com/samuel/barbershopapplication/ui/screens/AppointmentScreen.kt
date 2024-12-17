package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme

@Composable
fun AppointmentScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row {
      Text(text = "TELA AGENDAMENTO ")
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun AppointmentScreenPreview() {
  BarbershopApplicationTheme {
    AppointmentScreen()
  }
}