package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import br.com.samuel.barbershopapplication.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.samuel.barbershopapplication.ui.components.WeekCalendar
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun AppointmentScreen() {
  val currentDate = remember { LocalDate.now() }
  val currentMonth = remember { YearMonth.now() }
  val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() }
  val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() }
  val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
  val daysOfWeek = remember { daysOfWeek() }
  val state = rememberWeekCalendarState(
    startDate = startDate,
    endDate = endDate,
    firstVisibleWeekDate = currentDate,
    firstDayOfWeek = daysOfWeek.first()
  )
  var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
  val visibleMonth = remember(state.firstVisibleWeek) {
    YearMonth.from(state.firstVisibleWeek.days.first().date)
  }
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
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
    WeekCalendar()
    Row {
      Text(
        text = "Selecione o profissional"
      )
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