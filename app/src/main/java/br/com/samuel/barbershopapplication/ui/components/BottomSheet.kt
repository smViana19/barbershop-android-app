package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import br.com.samuel.barbershopapplication.ui.theme.LightOnSecondary
import br.com.samuel.barbershopapplication.ui.theme.LightPrimary
import br.com.samuel.barbershopapplication.ui.theme.LightPrimaryVariant
import br.com.samuel.barbershopapplication.ui.viewmodels.ScheduleViewModel
import br.com.samuel.barbershopapplication.utils.formatCurrency

@Composable
fun AppBottomSheet(
  onDismiss: () -> Unit,
  navController: NavController,
  userId: Int,
  professionalId: Int,
  serviceId: Int,
  availabilityId: Int,
  scheduleViewModel: ScheduleViewModel
) {
  val focusManager = LocalFocusManager.current
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
      .pointerInput(Unit) {
        detectTapGestures {
          focusManager.clearFocus()
        }
      },
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically

    ) {
      Text(
        text = "Confirme seu agendamento",
        style = TextStyle(
          fontSize = 24.sp,
          lineHeight = 28.8.sp,
          fontWeight = FontWeight(700),
          color = Color(LightOnSecondary.value),

          )
      )
    }
    HorizontalDivider(
      modifier = Modifier
        .padding(vertical = 14.dp),
      color = Color(LightPrimary.value)
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(12.dp))
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  text = scheduleViewModel.serviceName.value,
                  style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Gray
                  )
                )
              }
              Row {
                Column(
                  modifier = Modifier,
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Text(
                    text = "31",
                    style = TextStyle(
                      fontSize = 24.sp,
                      lineHeight = 24.sp,
                      fontWeight = FontWeight(500)
                    )
                  )
                  Text(
                    text = "DEZ",
                    style = TextStyle(
                      fontSize = 14.sp,
                      color = Color.Gray
                    )
                  )
                }
              }

            }
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(20.dp)
                  .clip(CircleShape)
                  .background(Color.Gray)
              )
              Text(
                modifier = Modifier.padding(start = 4.dp),
                text = scheduleViewModel.professionalName.value,
                style = TextStyle(
                  fontSize = 12.sp,
                  lineHeight = 24.sp,
                  color = Color.DarkGray
                )
              )
            }
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 2.dp),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "R$ ${scheduleViewModel.servicePrice.value.formatCurrency()}",
                style = TextStyle(
                  fontSize = 12.sp,
                  lineHeight = 24.sp,
                  color = Color(LightPrimaryVariant.value)
                )
              )
//              Text(
//                text = scheduleViewModel.availabilityTime.value, //TODO: AJUSTAR NO BACKEND
//                style = TextStyle(
//                  fontSize = 12.sp,
//                  lineHeight = 24.sp,
//                  color = Color.Black
//                )
//              )
            }

          }
        }
        Box(
          modifier = Modifier
            .align(Alignment.TopEnd)
            .offset(y = (-12).dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
            .clickable() {}
        ) {
          Box(
            modifier = Modifier
              .clip(
                RoundedCornerShape(
                  topStart = 4.dp,
                  topEnd = 4.dp,
                  bottomStart = 4.dp,
                  bottomEnd = 0.dp
                )
              )
              .clickable() {
                navController.navigate(NavigationScreens.HOME_SCREEN.name)
              }
              .background(Color(LightPrimary.value))
              .padding(horizontal = 8.dp, vertical = 4.dp)
          ) {
            Text(
              text = "Alterar serviço",
              color = Color.White,
              style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
              )
            )
          }
        }
      }
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
      ) {
        Row {
          Icon(
            modifier = Modifier
              .padding(end = 4.dp),
            tint = Color.Gray,
            painter = painterResource(R.drawable.ic_message_24),
            contentDescription = ""
          )
          Text(
            text = "Alguma observação?",
            style = TextStyle(
              fontSize = 16.sp,
              color = Color.Gray
            )
          )
        }
        OutlinedTextField(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
          value = scheduleViewModel.details.value,
          onValueChange = { newValue ->
            scheduleViewModel.details.value = newValue
          },
          placeholder = {
            Text(
              text = "Ex: Nao precisa lavar",
              style = TextStyle(
                color = Color.Gray
              )
            )
          },
          colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White
          ),
        )
      }
    }
    Button(
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        scheduleViewModel.createAppointment(
          userId,
          professionalId,
          serviceId,
          availabilityId,
          details = scheduleViewModel.details.value
        )
        onDismiss()
      },
      shape = RoundedCornerShape(8.dp)
    ) {
      Text(text = "Confirmar e agendar")
    }
    Spacer(modifier = Modifier.padding(16.dp))
  }
}

@Preview(showBackground = true)
@Composable
private fun AppBottomSheetSPreview() {
  BarbershopApplicationTheme {

//    AppBottomSheetS()
//    AppBottomSheet(onDismiss = {})
  }
}