package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF0F172A))
  ) {
    Column(
      modifier = Modifier.weight(1f)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(end = 16.dp, top = 32.dp),
        horizontalArrangement = Arrangement.End
      ) {
        Box(
          modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Gray)
        )
      }
      Column {
        Row {
          Text("Nome usuário")
        }
        Row {

        }
      }
    }

    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .background(Color.White)
    ) {
      Text("meu")
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
  val navController = rememberNavController()
  HomeScreen(navController)
}
