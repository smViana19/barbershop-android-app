package br.com.samuel.barbershopapplication.ui.screens

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiAppointmentServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiAvailabilityServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiProfessionalServiceMock
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import br.com.samuel.barbershopapplication.model.ApiServiceResponse
import br.com.samuel.barbershopapplication.ui.components.AppBottomSheet
import br.com.samuel.barbershopapplication.ui.components.WeekCalendar
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import br.com.samuel.barbershopapplication.ui.theme.BarbershopApplicationTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.ScheduleViewModel
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
  selectedDate: String,
  serviceId: Int, //TODO: ADICIONAR PARA CRIAR O AGENDAMENTO (BOTTOM SHEETS)
  navController: NavController,
//  sharedPrefs: SharedPrefsService,
  scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
  val professionals = scheduleViewModel.professionals
  var professionalIdToAllComponents by remember { mutableIntStateOf(-1) }
  var availabilityIdToAllComponents by remember { mutableIntStateOf(-1) }
  val availabilities by scheduleViewModel.filteredAvailabilities.collectAsState()
  val currentDate = remember { LocalDate.now() }
  val currentMonth = remember { YearMonth.now() }
  val startDate = remember { currentMonth.minusMonths(1).atStartOfMonth() }
  val endDate = remember { currentMonth.plusMonths(12).atEndOfMonth() }
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
  val parsedDate = remember {
    if (selectedDate.isNotEmpty()) {
      LocalDate.parse(selectedDate)
    } else {
      LocalDate.now()
    }
  }
  var currentSelectedDate by remember { mutableStateOf(parsedDate) }

  LaunchedEffect(Unit) {
    scheduleViewModel.getAllProfessionals()
  }
  LaunchedEffect(selectedDate) {
    scheduleViewModel.filteredDate(selectedDate)
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
        onClick = {
          navController.popBackStack()
        },
      ) {
        Icon(painter = painterResource(R.drawable.ic_back_24), contentDescription = "back")
      }
      Text(
        text = visibleMonth.month.getDisplayName(
          TextStyle.FULL,
          Locale.getDefault()
        ) + " " + visibleMonth.year
      )
      IconButton(onClick = {
        navController.navigate(NavigationScreens.CALENDAR_SCREEN.name)
      }) {
        Icon(painter = painterResource(R.drawable.ic_calendar_24), contentDescription = "calendar")
      }
    }
    WeekCalendar(
      currentSelectedDate = currentSelectedDate,
      scheduleViewModel = scheduleViewModel
    )

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
          professionalIdToAllComponents = professionalId
          println("professionalIdToAllComponents: $professionalIdToAllComponents")
        }
      )
      HorizontalDivider()
    }
    AvailableTimesList(
      availabilities = availabilities,
      navController = navController,
      userId = 17, //TODO: SO PRA TESTE
      serviceId = serviceId,
      professionalId = professionalIdToAllComponents,
      onClick = { availabilityId ->
        availabilityIdToAllComponents = availabilityId
      },
      availabilityId = availabilityIdToAllComponents,
      scheduleViewModel = scheduleViewModel

    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableTimesList(
  availabilities: List<ApiAvailabilityResponse>,
  navController: NavController,
  userId: Int,
  serviceId: Int,
  onClick: (Int) -> Unit,
  professionalId: Int,
  availabilityId: Int,
//  service: ApiServiceResponse?,
//  professional: ApiProfessionalResponse?,
//  availability: ApiAvailabilityResponse?,
  scheduleViewModel: ScheduleViewModel
) {
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
        val sheetState = rememberModalBottomSheetState(
          skipPartiallyExpanded = true,
          confirmValueChange = { true }
        )
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = Color.White,
          shadowElevation = 4.dp,
          modifier = Modifier
            .padding(8.dp)
            .clickable() {
              onClick(availableTime.id)
              showBottomSheet = true
            }
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
          Box(
            modifier = Modifier
              .fillMaxSize()
              .padding(16.dp) //MELHORAR DPS
          ) {
            if (showBottomSheet) {
              ModalBottomSheet(
                onDismissRequest = {
                  showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = Color.White,
                dragHandle = {
                  Spacer(
                    modifier = Modifier
                      .padding(bottom = 24.dp, top = 8.dp)
                      .height(3.dp)
                      .width(38.dp)
                      .clip(CircleShape)
                      .background(Color(0xFFE0E0E0))
                  )
                },
              ) {
                AppBottomSheet(
                  onDismiss = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                      if (!sheetState.isVisible) {
                        showBottomSheet = false
                      }
                    }
                  },
                  navController = navController,
                  userId = userId,
                  serviceId = serviceId,
                  professionalId = professionalId,
                  availabilityId = availabilityId,
                  scheduleViewModel = scheduleViewModel
                )
              }
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ScheduleScreenPreview() {
  val selectedDate = ""
  val serviceId = 1
  val navController = rememberNavController()
  val apiAvailabilityServiceMock = ApiAvailabilityServiceMock()
  val apiAppointmentService = ApiAppointmentServiceMock()
  val apiProfessionalServiceMock = ApiProfessionalServiceMock()
  val scheduleViewmodel =
    ScheduleViewModel(apiAvailabilityServiceMock, apiProfessionalServiceMock, apiAppointmentService)

  BarbershopApplicationTheme {
    ScheduleScreen(selectedDate, serviceId, navController, scheduleViewmodel)
  }
}