import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.*
import com.kizitonwose.calendar.core.*
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun VerticalCalendarExample() {
  val currentMonth = remember { YearMonth.now() }
  val startMonth = currentMonth.minusMonths(1)
  val endMonth = currentMonth.plusMonths(12)

  val state = rememberCalendarState(
    startMonth = startMonth,
    endMonth = endMonth,
    firstVisibleMonth = currentMonth
  )

  val visibleMonth = remember(state.firstVisibleMonth) {
    state.firstVisibleMonth.yearMonth
  }

  Column(modifier = Modifier.fillMaxSize()) {
    Text(
      text = visibleMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
          + " " + visibleMonth.year,
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    )

    VerticalCalendar(
      state = state,
      monthHeader = { month ->
        Text(
          text = month.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
              + " " + month.yearMonth.year,
          modifier = Modifier.padding(8.dp),
          style = MaterialTheme.typography.titleMedium
        )
      },
      dayContent = { day ->
        DayContent(day)
      },
    )
  }
}

@Composable
fun DayContent(day: CalendarDay) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(text = day.date.dayOfMonth.toString())
  }
}


@Preview(showBackground = true)
@Composable
private fun VerticalCalendarExamplePrev() {
  VerticalCalendarExample()
}
