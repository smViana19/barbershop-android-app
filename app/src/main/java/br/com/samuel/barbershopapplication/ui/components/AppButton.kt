package br.com.samuel.barbershopapplication.ui.components

import android.widget.Space
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.theme.AppTheme

@Composable
fun AppButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  isLoading: Boolean = false,
  text: String,
  leadingIcon: (@Composable (() -> Unit))? = null,
  trailingIcon: (@Composable (() -> Unit))? = null,
  textColor: Color = Color.White,
  enabled: Boolean = true,
  shape: Shape = ButtonDefaults.shape,
  colors: ButtonColors = ButtonDefaults.buttonColors(),
  elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
  border: BorderStroke? = null,
  fontSize: TextUnit = TextUnit.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
  iconSpacing: Dp = 8.dp
) {
  val alpha by animateFloatAsState(if (isLoading) 0.5f else 1f, label = "")
  val buttonColors = if (enabled) {
    colors
  } else {
    ButtonDefaults.buttonColors(
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), // ou 0.9f dependendo do seu estilo
      disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    )
  }
  Button(
    modifier = modifier.alpha(alpha),
    onClick = {
      if (!isLoading) {
        onClick()
      }
    },
    enabled = enabled && !isLoading,
    shape = shape,
    colors = buttonColors,
    elevation = elevation,
    border = border,
    contentPadding = contentPadding
  ) {
    if (isLoading) {
      CircularProgressIndicator(
        modifier = Modifier.size(24.dp),
        color = textColor,
        strokeWidth = 2.dp
      )
    } else {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        if (leadingIcon != null) {
          leadingIcon()
          Spacer(modifier = Modifier.width(iconSpacing))
        }
        Text(
          text = text,
          color = textColor,
          fontSize = fontSize,
          fontStyle = fontStyle,
          fontWeight = fontWeight,
          fontFamily = fontFamily
        )
        if (trailingIcon != null) {
          Spacer(modifier = Modifier.width(iconSpacing))
          trailingIcon()
        }
      }

    }
  }
}

@Preview()
@Composable
private fun AppButtonPreview() {
  AppTheme {
    var isLoading by remember { mutableStateOf(false) }

    AppButton(
      onClick = {},
      text = "Botao simples",
      isLoading = isLoading,
      trailingIcon = {
        Icon(painter = painterResource(R.drawable.ic_camera_24), contentDescription = "")
      }

    )
  }
}