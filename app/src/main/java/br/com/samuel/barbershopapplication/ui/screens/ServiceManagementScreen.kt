package br.com.samuel.barbershopapplication.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiProfessionalServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiServiceServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiSpecialtyServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.SharedPrefsServiceMock
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import br.com.samuel.barbershopapplication.model.ApiServiceResponse
import br.com.samuel.barbershopapplication.model.ApiSpecialtyResponse
import br.com.samuel.barbershopapplication.ui.components.AppButton
import br.com.samuel.barbershopapplication.ui.components.ListSkeletonLoader
import br.com.samuel.barbershopapplication.ui.navigation.NavigationScreens
import br.com.samuel.barbershopapplication.ui.theme.AppTheme
import br.com.samuel.barbershopapplication.ui.viewmodels.ServiceManagementViewModel
import br.com.samuel.barbershopapplication.utils.formatCurrency
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceManagementScreen(
  serviceManagementViewModel: ServiceManagementViewModel = hiltViewModel(),
  navController: NavController
) {
  var showExitDialog by remember { mutableStateOf(false) }
  val servicesData = serviceManagementViewModel.serviceData
  val professionals = serviceManagementViewModel.professionals
  val specialties = serviceManagementViewModel.specialties
  val focusManager = LocalFocusManager.current
  val tabs = listOf("Serviços", "Profissionais", "Especializações", "Detalhes")
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  LaunchedEffect(selectedTabIndex) {
    serviceManagementViewModel.verifyIsUserLoggedIn(navController = navController)
    when (selectedTabIndex) {
      0 -> if (serviceManagementViewModel.serviceData.value.isEmpty()) serviceManagementViewModel.getAllServices()
      1 -> if (serviceManagementViewModel.professionals.value.isEmpty()) serviceManagementViewModel.getAllProfessionals()
      2 -> if (serviceManagementViewModel.specialties.value.isEmpty()) serviceManagementViewModel.getAllSpecialties()
    }
  }

  BackHandler {
    showExitDialog = true
  }

  if (showExitDialog) {
    AlertDialog(
      onDismissRequest = { showExitDialog = false },
      title = { Text("Sair") },
      text = { Text("Tem certeza que deseja sair do aplicativo?") },
      confirmButton = {
        TextButton(onClick = { showExitDialog = false }) {
          Text("Cancelar")
        }
      },
      dismissButton = {
        TextButton(onClick = { exitProcess(0) }) {
          Text("Sair")
        }
      }
    )
  }
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(text = "Barbearia")
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.navigate(NavigationScreens.SERVICE_MANAGEMENT_SCREEN.name)
          }) {
            Icon(Icons.Filled.ArrowBack, "backIcon")
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
          .padding(it)
          .pointerInput(Unit) {
            detectTapGestures {
              focusManager.clearFocus()
            }
          }
          .background(Color.White)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            containerColor = Color.Transparent,
            contentColor = Color(0xFF3B3B3B),
          ) {
            tabs.forEachIndexed { index, title ->
              Tab(selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { Text(text = title) }
              )
            }
          }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        if (serviceManagementViewModel.isLoading.value) {
          ListSkeletonLoader()
        }
        Column(modifier = Modifier.padding()) {
          when (selectedTabIndex) {
            0 -> ServicesTab(services = servicesData.value, navController = navController)
            1 -> ProfessionalsTab(professionals = professionals.value)
            2 -> SpecialtiesTab(specialties = specialties.value)
          }
        }
      }
    }
  )

}

@Composable
fun ServicesTab(services: List<ApiServiceResponse>, navController: NavController) {

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    horizontalArrangement = Arrangement.Center

  ) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = "",
      onValueChange = {},
      shape = RoundedCornerShape(8.dp),
      label = { Text(text = "Pesquisar serviço") },
      leadingIcon = {
        Icon(
          painter = painterResource(R.drawable.ic_search_24),
          contentDescription = "Search"
        )
      },
      trailingIcon = {},
    )
  }
  Spacer(modifier = Modifier.padding(8.dp))

  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ) {
    items(services) { service ->
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(
              modifier = Modifier,
              text = service.name,
              fontSize = 16.sp,
            )
          }
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              modifier = Modifier.padding(end = 8.dp),
              text = "R$ ${service.price.formatCurrency()}",
              fontSize = 14.sp
            )
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                modifier = Modifier
                  .size(16.dp)
                  .padding(end = 4.dp),
                painter = painterResource(R.drawable.ic_time_24),
                contentDescription = "time"
              )
              Text(
                text = "30min",
                fontSize = 14.sp
              )
            }
          }
        }
        Column(modifier = Modifier.weight(1f)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            AppButton(
              onClick = {
                navController.navigate("${NavigationScreens.SCHEDULE_SCREEN.name}?serviceId=${service.id}")
              },
              text = "Agendar",
              contentPadding = PaddingValues(horizontal = 16.dp),
              shape = RoundedCornerShape(8.dp),
              trailingIcon = {
                Icon(painter = painterResource(R.drawable.ic_add_24), contentDescription = "")
              }
            )
          }
        }
      }
      HorizontalDivider(
        modifier = Modifier.padding(horizontal = 4.dp),
        color = Color.LightGray,
        thickness = 1.dp
      )
    }
  }
}

@Composable
fun ProfessionalsTab(professionals: List<ApiProfessionalResponse>) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    horizontalArrangement = Arrangement.Center
  ) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = "",
      onValueChange = {},
      shape = RoundedCornerShape(8.dp),
      label = { Text(text = "Pesquisar") },
      leadingIcon = {
        Icon(
          painter = painterResource(R.drawable.ic_search_24),
          contentDescription = "Search"
        )
      },
      trailingIcon = {},
    )
  }
  Spacer(modifier = Modifier.padding(8.dp))

  if (professionals.isEmpty()) {
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
          text = "Nenhum item para exibir",
          fontSize = 16.sp
        )
      }
    }

  } else {
    LazyColumn(
      modifier = Modifier.fillMaxSize()
    ) {
      items(professionals) { professional ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                //TODO: COLOCAR A IMAGEM DO PROFISSIONAL
                modifier = Modifier
                  .size(50.dp)
                  .clip(CircleShape)
                  .background(Color.Gray)
              )
              Text(
                modifier = Modifier
                  .padding(start = 4.dp),
                text = professional.user.name.lowercase()
              )
            }

          }
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.End
            ) {
              AppButton(
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                text = "Veja mais",
                onClick = {}
              )
            }
          }
        }
        HorizontalDivider()
      }
    }
  }


}

@Composable
fun SpecialtiesTab(specialties: List<ApiSpecialtyResponse>) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    horizontalArrangement = Arrangement.Center
  ) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = "",
      onValueChange = {},
      shape = RoundedCornerShape(8.dp),
      label = { Text(text = "Pesquisar especialidade") },
      leadingIcon = {
        Icon(
          painter = painterResource(R.drawable.ic_search_24),
          contentDescription = "Search"
        )
      },
      trailingIcon = {},
    )
  }
  Spacer(modifier = Modifier.padding(8.dp))
  if (specialties.isEmpty()) {
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
          text = "Nenhum item para exibir",
          fontSize = 16.sp
        )
      }
    }
  } else {
    LazyColumn(
      modifier = Modifier.fillMaxSize()
    ) {
      items(specialties) { specialty ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(50.dp)
                  .clip(CircleShape)
                  .background(Color.Gray)
              )
              Text(
                modifier = Modifier
                  .padding(start = 4.dp),
                text = specialty.name
              )
            }
            Row {
              Text(text = specialty.description)
            }

          }
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.End
            ) {
              AppButton(
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                text = "Veja mais",
                onClick = {}
              )
            }
          }
        }
        HorizontalDivider()

      }
    }
  }
  Text("Teste especialidades")

}

@Preview(showBackground = true)
@Composable
private fun ServiceManagementPreview() {
  val navController = rememberNavController()
  val apiServiceServiceMock = ApiServiceServiceMock()
  val apiProfessionalServiceMock = ApiProfessionalServiceMock()
  val apiSpecialtyServiceMock = ApiSpecialtyServiceMock()
  val sharedPrefsServiceMock = SharedPrefsServiceMock()

  val serviceManagementViewModel = ServiceManagementViewModel(
    sharedPrefsServiceMock,
    apiProfessionalServiceMock,
    apiServiceServiceMock,
    apiSpecialtyServiceMock
  )
  AppTheme {
    ServiceManagementScreen(serviceManagementViewModel, navController)
  }
}