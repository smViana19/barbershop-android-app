package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.ui.components.DayMonth
import br.com.samuel.barbershopapplication.ui.components.DaysOfWeekTitle
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
  navController: NavController
) {

  val currentMonth = remember { YearMonth.now() }
  val startMonth = remember { currentMonth.minusMonths(1) }
  val endMonth = remember { currentMonth.plusMonths(12) }
  val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
  val daysOfWeek = remember { daysOfWeek() }
  val state = rememberCalendarState(
    startMonth = startMonth,
    endMonth = endMonth,
    firstVisibleMonth = currentMonth,
    firstDayOfWeek = firstDayOfWeek
  )
  var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
  val visibleMonth = remember(state.firstVisibleMonth) {
    state.firstVisibleMonth.yearMonth
  }

  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(text = "Calendário")
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(NavigationScreens.SCHEDULE_SCREEN.name)
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
    },
    content = {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(it),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        VerticalCalendar(
          state = state,
          dayContent = { day ->
            DayMonth(
              day = day,
              selectedDate = selectedDate,
              calendarState = state,
              onClick = { clickableDate ->
                selectedDate = clickableDate
                val formattedDate = clickableDate.toString()
                navController.navigate("${NavigationScreens.SCHEDULE_SCREEN.name}?selectedDate=$formattedDate")
              }
            )
          },
          monthHeader = { month ->
            val month =
              month.yearMonth.month.getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
                .uppercase() +
                  " " + month.yearMonth.year
            Column(
              modifier = Modifier.padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                modifier = Modifier.padding(8.dp),
                text = month,
                style = MaterialTheme.typography.titleMedium
              )
              DaysOfWeekTitle(daysOfWeek)
            }

          },
        )
      }
    }
  )

}


@Preview(showBackground = true)
@Composable
private fun CalendarScreenPreview() {
  val navController = rememberNavController()
  CalendarScreen(navController)
}