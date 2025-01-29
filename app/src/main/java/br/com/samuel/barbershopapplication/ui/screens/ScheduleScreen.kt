package br.com.samuel.barbershopapplication.ui.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiServiceServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.SharedPrefsServiceMock
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsService
import br.com.samuel.barbershopapplication.model.ApiAvailabilityResponse
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import br.com.samuel.barbershopapplication.ui.components.AppBottomSheet
import br.com.samuel.barbershopapplication.ui.components.AppCustomTopAppBar
import br.com.samuel.barbershopapplication.ui.components.WeekCalendar
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import br.com.samuel.barbershopapplication.ui.theme.AppTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.ScheduleViewModel
import br.com.samuel.barbershopapplication.utils.formatTime
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
  serviceId: Int,
  navController: NavController,
  sharedPrefs: SharedPrefsService,
  scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {

  val userId = sharedPrefs.getUserId()
  val professionals = scheduleViewModel.professionals
  var professionalIdToAllComponents by remember { mutableIntStateOf(-1) }
  var availabilityIdToAllComponents by remember { mutableIntStateOf(-1) }
  val availabilities by scheduleViewModel.filteredAvailabilities.collectAsState()
  var visibleMonth by remember { mutableStateOf(YearMonth.now()) }
  val parsedDate = remember {
    if (selectedDate.isNotEmpty()) {
      LocalDate.parse(selectedDate)
    } else {
      LocalDate.now()
    }
  }
  val currentSelectedDate by remember { mutableStateOf(parsedDate) }

  val monthTitle = visibleMonth.month.getDisplayName(
    TextStyle.FULL,
    Locale("pt", "BR") //TODO: MUDAR QUANDO FOR COLOCAR PARA SELECIONAR LINGUAGEM
  ) + " " + visibleMonth.year

  LaunchedEffect(Unit) {
    scheduleViewModel.getAllProfessionals()
  }
  LaunchedEffect(selectedDate) {
    scheduleViewModel.filteredDate(selectedDate)
  }
  LaunchedEffect(professionalIdToAllComponents) {
    scheduleViewModel.getServiceById(serviceId)
    scheduleViewModel.getProfessionalById(professionalIdToAllComponents)
  }

  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(text = monthTitle)
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(NavigationScreens.SERVICE_MANAGEMENT_SCREEN.name)
          }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
          }

        },
        actions = {
          IconButton(onClick = {
            navController.navigate(NavigationScreens.CALENDAR_SCREEN.name) {
              launchSingleTop = true
            }
          }) {
            Icon(
              painter = painterResource(R.drawable.ic_calendar_24),
              contentDescription = "calendar"
            )
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .fillMaxSize()
          .padding(it)
      ) {
        WeekCalendar(
          currentSelectedDate = currentSelectedDate,
          scheduleViewModel = scheduleViewModel,
          onMonthChange = { newMonth ->
            visibleMonth = newMonth
          }
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
            }
          )
          HorizontalDivider()
        }
        AvailableTimesList(
          availabilities = availabilities,
          navController = navController,
          userId = userId,
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
  )
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
            .clickable {
              onClick(availableTime.id)
              scheduleViewModel.getAvailabilityById(availabilityId)
              showBottomSheet = true
            }
        ) {
          if (!availableTime.isAvailable) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ) {
              Text(
                text = formatTime(availableTime.time),
                color = Color.Black
              )
            }
            Box(
              modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
}

@Preview(showBackground = true)
@Composable
private fun ScheduleScreenPreview() {
  val selectedDate = ""
  val serviceId = 1
  val navController = rememberNavController()
  val apiAvailabilityServiceMock = ApiAvailabilityServiceMock()
  val sharedPrefsServiceMock = SharedPrefsServiceMock()
  val apiAppointmentService = ApiAppointmentServiceMock()
  val apiProfessionalServiceMock = ApiProfessionalServiceMock()
  val apiServiceServiceMock = ApiServiceServiceMock()
  val scheduleViewmodel =
    ScheduleViewModel(
      apiAvailabilityServiceMock,
      apiProfessionalServiceMock,
      apiAppointmentService,
      apiServiceServiceMock
    )

  AppTheme {
    ScheduleScreen(
      selectedDate,
      serviceId,
      navController,
      sharedPrefsServiceMock,
      scheduleViewmodel
    )
  }
}