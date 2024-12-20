package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiAvailabilityServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiProfessionalServiceMock
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import br.com.samuel.barbershopapplication.ui.components.WeekCalendar
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.AvailabilityViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.ProfessionalViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.ScheduleViewModel
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ScheduleScreen(
  scheduleViewModel: ScheduleViewModel = hiltViewModel()
//  availabilityViewModel: AvailabilityViewModel = hiltViewModel(),
//  professionalViewModel: ProfessionalViewModel = hiltViewModel()
) {
  val professionals = scheduleViewModel.professionals
  val availabilities by scheduleViewModel.filteredAvailabilities.collectAsState()

  val currentDate = remember { LocalDate.now() }
  val currentMonth = remember { YearMonth.now() }
  val startDate = remember { currentMonth.minusMonths(1).atStartOfMonth() }
  val endDate = remember { currentMonth.plusMonths(12).atEndOfMonth() }
  val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
  val daysOfWeek = remember { daysOfWeek() }
  val state = rememberWeekCalendarState(
    startDate = startDate,
    endDate = endDate,
    firstVisibleWeekDate = currentDate,
    firstDayOfWeek = daysOfWeek.first()
  )
  val visibleMonth = remember(state.firstVisibleWeek) {
    YearMonth.from(state.firstVisibleWeek.days.first().date)
  }

  LaunchedEffect(Unit) {
    scheduleViewModel.getAllProfessionals()
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 16.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(
        onClick = {},
      ) {
        Icon(painter = painterResource(R.drawable.ic_back_24), contentDescription = "back")
      }
      Text(
        text = visibleMonth.month.getDisplayName(
          TextStyle.FULL,
          Locale.getDefault()
        ) + " " + visibleMonth.year
      )
      IconButton(onClick = {}) {
        Icon(painter = painterResource(R.drawable.ic_calendar_24), contentDescription = "calendar")
      }
    }
    WeekCalendar(scheduleViewModel = scheduleViewModel)

    Column(modifier = Modifier.padding(top = 16.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
      ) {
        Text(
          text = "Selecione o profissional",
          fontSize = 18.sp,
        )
      }
      ProfessionalList(
        professionals = professionals.value,
        onClick = { professionalId ->
          scheduleViewModel.getAvailabilitiesByProfessionalId(professionalId)
          println(professionalId)
        }
      )
      HorizontalDivider()
    }
    AvailableTimesList(availabilities = availabilities)
  }
}

@Composable
fun ProfessionalList(professionals: List<ApiProfessionalResponse>, onClick: (Int) -> Unit) {
  LazyRow(
    modifier = Modifier
      .padding(4.dp),
    contentPadding = PaddingValues(4.dp)
  ) {
    if (professionals.isNotEmpty()) {
      items(professionals) { professional ->
        Column(
          modifier = Modifier
            .clickable {
              onClick(professional.id)
            },
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Box(
            //TODO: COLOCAR A IMAGEM DO PROFISSIONAL
            modifier = Modifier
              .size(50.dp)
              .clip(CircleShape)
              .background(Color.Gray)
          )
          Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = professional.user.name.lowercase(),
            fontSize = 12.sp
          )
        }
      }
    }
  }
}

@Composable
fun AvailableTimesList(availabilities: List<ApiAvailabilityResponse>) {
  if (availabilities.isEmpty()) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 24.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
      ) {
        Text(
          text = "Nenhum horário disponivel",
          fontSize = 16.sp
        )
      }
    }
  } else {
    LazyVerticalGrid(
      modifier = Modifier
        .padding(PaddingValues(top = 8.dp)),
      columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
      items(availabilities) { availableTime ->
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = Color.White,
          shadowElevation = 4.dp,
          modifier = Modifier.padding(8.dp)
        ) {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .fillMaxSize()
              .padding(16.dp)
          ) {
            Text(
              text = availableTime.time,
              color = Color.Black
            )
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ScheduleScreenPreview() {
  val apiAvailabilityServiceMock = ApiAvailabilityServiceMock()
  val apiProfessionalServiceMock = ApiProfessionalServiceMock()
  val scheduleViewmodel = ScheduleViewModel(apiAvailabilityServiceMock, apiProfessionalServiceMock)

  BarbershopApplicationTheme {
    ScheduleScreen(scheduleViewmodel)
  }
}