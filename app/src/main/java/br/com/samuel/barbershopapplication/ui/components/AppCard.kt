package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.theme.AppTheme
import br.com.samuel.barbershopapplication.utils.formatTime

@Composable
fun AppCardAppointment(
  appointmentId: Int,
  time: String,
  date: String
) {
  Card(
    modifier = Modifier
      .fillMaxWidth(),
    elevation = CardDefaults.cardElevation(1.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    shape = RoundedCornerShape(8.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .background(Color.White),
      contentAlignment = Alignment.Center
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Column(
          modifier = Modifier.padding(start = 8.dp, end = 14.dp)
        ) {
          Box(
            modifier = Modifier
              .size(12.dp)
              .clip(CircleShape)
              .background(Color(0xFFF49D0B))
          )
        }
        Column(
          modifier = Modifier.weight(1f),
        ) {
          Text(
            text = formatTime(time),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = date
          )
        }
        Row(
          modifier = Modifier.padding(end = 8.dp),
          horizontalArrangement = Arrangement.End
        ) {
          AppButton(
            onClick = {},
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color.White,
              contentColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color(0xFFE1E7EF)),
            icon = {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_edit_24),
                contentDescription = "Edit Icon",
              )
            }
          )
          Spacer(modifier = Modifier.width(8.dp))
          AppButton(
            onClick = {},
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFEE4444),
              contentColor = Color.White,
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color(0xFFE1E7EF)),
            icon = {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_delete_24),
                contentDescription = "Delete Icon",
              )
            })
        }
      }


    }
  }
}

@Preview(showBackground = true)
@Composable
private fun AppCardAppointmentPreview() {
  AppTheme {
    var appointmentId = 1
    var time = "10:00"
    var date = "2025-01-24"
    AppCardAppointment(appointmentId, time, date)
  }
}