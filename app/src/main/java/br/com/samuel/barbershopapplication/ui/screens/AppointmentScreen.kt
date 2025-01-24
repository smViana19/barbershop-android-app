package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiAppointmentServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.SharedPrefsServiceMock
import br.com.samuel.barbershopapplication.ui.components.AppButton
import br.com.samuel.barbershopapplication.ui.components.AppCardAppointment
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import br.com.samuel.barbershopapplication.ui.theme.AppTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.AppointmentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
  appointmentViewModel: AppointmentViewModel,
  navController: NavController,
) {
  LaunchedEffect(Unit) {
    appointmentViewModel.getPendingUserAppointments()
  }
  var appointments = appointmentViewModel.appointments.value
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = "Meus agendamentos")
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(NavigationScreens.HOME_SCREEN.name)
          }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
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
        LazyColumn(
          modifier = Modifier
            .weight(1f)
            .padding(top = 16.dp)
        ) {
          items(appointments) { appointment ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
              AppCardAppointment(
                appointment.id,
                appointment.availability.time,
                appointment.availability.date
              )
            }
          }

        }

        Column(
          modifier = Modifier
            .padding(16.dp)
        ) {
          AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Histórico",
            textColor = MaterialTheme.colorScheme.primary,
            colors = ButtonDefaults.buttonColors(
              containerColor = Color.White,
              contentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(1.dp, Color(0xFFE1E7EF)),
            shape = RoundedCornerShape(8.dp)
          )
          AppButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Novo agendamento",
            colors = ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.primary,
              contentColor = Color.White
            ),
            trailingIcon = {
              Icon(painter = painterResource(R.drawable.ic_add_24), contentDescription = "")
            },
            shape = RoundedCornerShape(8.dp)
          )
        }
      }
    })
}


@Preview(showBackground = true)
@Composable
private fun AppointmentScreenPreview() {
  val navController = rememberNavController()
  val apiAppointmentService = ApiAppointmentServiceMock()
  val sharedPrefsServiceMock = SharedPrefsServiceMock()
  val appointmentViewModel = AppointmentViewModel(apiAppointmentService, sharedPrefsServiceMock)
  AppTheme {
    AppointmentScreen(appointmentViewModel, navController)
  }
}