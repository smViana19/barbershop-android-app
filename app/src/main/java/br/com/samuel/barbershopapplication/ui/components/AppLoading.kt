package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme

@Composable
fun AppLoading() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .zIndex(1f)
      .background(Color.Black.copy(alpha = 0.7f)),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      CircularProgressIndicator(modifier = Modifier.size(48.dp))
      Spacer(modifier = Modifier.padding(16.dp))
      Text(
        text = "Carregando...",
        color = Color.White
      )
    }

  }
}

@Preview(showBackground = true)
@Composable
private fun AppLoadingPreview() {
  BarbershopApplicationTheme {
    AppLoading()
  }
}