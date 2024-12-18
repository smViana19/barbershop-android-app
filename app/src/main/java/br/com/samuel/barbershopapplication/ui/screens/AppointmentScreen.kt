package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.background
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
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.components.WeekCalendar
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun AppointmentScreen() {
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
  var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
  val visibleMonth = remember(state.firstVisibleWeek) {
    YearMonth.from(state.firstVisibleWeek.days.first().date)
  }

  Column (
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
    WeekCalendar()

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
      LazyRow(
        modifier = Modifier.padding(2.dp),
        contentPadding = PaddingValues(4.dp)
      ) {
        items(3) { professional ->
          Column(
            modifier = Modifier,
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
              modifier = Modifier.padding(horizontal = 4.dp),
              text = "Profissional",
              fontSize = 12.sp
            )
          }
        }
      }
      HorizontalDivider()
    }
    LazyVerticalGrid(
      modifier = Modifier
        .weight(1f)
        .padding(PaddingValues(top = 8.dp)),
      columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
      items(20) { availableTime ->
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
              text = "13:00",
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
private fun AppointmentScreenPreview() {
  BarbershopApplicationTheme {
    AppointmentScreen()
  }
}