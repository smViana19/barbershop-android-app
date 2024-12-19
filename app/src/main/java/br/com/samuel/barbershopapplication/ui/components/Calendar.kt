package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.AvailabilityViewModel
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.CalendarDay
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
fun Calendar(modifier: Modifier = Modifier) {

}

@Composable
fun WeekCalendar(availabilityViewModel: AvailabilityViewModel) {
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
  DaysOfWeekTitle(daysOfWeek = daysOfWeek)
  WeekCalendar(
    state = state,
    dayContent = { day ->
      Day(
        day = day,
        selectedDate = selectedDate,
        onClick = { clickableDate ->
          selectedDate = clickableDate
          println("dia selecionado: $clickableDate")
          availabilityViewModel.selectDate(clickableDate.toString())
        })
    }
  )
  HorizontalDivider()
}

@Composable
fun Day(day: WeekDay, selectedDate: LocalDate?, onClick: (LocalDate) -> Unit) {
  val isSelected = selectedDate == day.date
  val interactionSource = remember { MutableInteractionSource() }
  Box(
    modifier = Modifier
      .aspectRatio(1f)
      .padding(8.dp)
      .background(
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        shape = CircleShape,
      )
      .clickable(
        interactionSource = interactionSource,
        indication = null,
      ) {
        onClick(day.date)
      },
    contentAlignment = Alignment.Center
  ) {
    Text(
      fontSize = 16.sp,
      text = day.date.dayOfMonth.toString(),
      textAlign = TextAlign.Center,
      color = if (isSelected) Color.White else Color.Black

    )
  }
}

@Composable
fun DayMonth(day: CalendarDay, onClick: (CalendarDay) -> Unit) {
  Box(
    modifier = Modifier
      .aspectRatio(1f)
      .padding(8.dp),
//      .background(
//        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
//        shape = CircleShape,
//      )
//      .clickable(
//        interactionSource = interactionSource,
//        indication = null,
//      ) {
//        onClick(day.date)
//      },
    contentAlignment = Alignment.Center
  ) {
    Text(
      fontSize = 16.sp,
      text = day.date.dayOfMonth.toString(),
      textAlign = TextAlign.Center,
    )
  }
}


@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
  Row(modifier = Modifier.fillMaxWidth()) {
    for (dayOfWeek in daysOfWeek) {
      Text(
        modifier = Modifier.weight(1f),
        textAlign = TextAlign.Center,
        color = Color.Gray,
        text = dayOfWeek.getDisplayName(
          TextStyle.SHORT,
          Locale("pt", "BR")
        ), //TODO MUDAR QUANDO CRIAR MINHA LIB DE CONVERSOR DE DATA
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun CalendarPreview() {
  BarbershopApplicationTheme {
//    WeekCalendar()
  }
}