package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.theme.AppTheme

@Composable
fun AppCustomTopAppBar(
  title: String,
  leadingIcon: @Composable () -> Unit,
  trailingIcon: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
  contentColor: Color = Color.White
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .background(backgroundColor)
      .height(64.dp)
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    leadingIcon()
    Text(
      text = title,
      style = MaterialTheme.typography.titleLarge,
      color = contentColor,
      modifier = Modifier.padding(horizontal = 16.dp)
    )
    trailingIcon()
  }
}

@Preview
@Composable
private fun AppCustomTopAppBarPreview() {
  AppTheme {
    AppCustomTopAppBar(
      title = "TopBar",
      leadingIcon = {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
      },
      trailingIcon = {
        Icon(
          painter = painterResource(R.drawable.ic_calendar_24),
          contentDescription = ""
        )
      }
    )
  }
}