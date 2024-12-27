package br.com.samuel.barbershopapplication.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
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
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiProfessionalServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiServiceServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.ApiSpecialtyServiceMock
import br.com.samuel.barbershopapplication.backendservices.mocks.SharedPrefsServiceMock
import br.com.samuel.barbershopapplication.model.ApiProfessionalResponse
import br.com.samuel.barbershopapplication.model.ApiServiceResponse
import br.com.samuel.barbershopapplication.model.ApiSpecialtyResponse
import br.com.samuel.barbershopapplication.ui.viewmodels.HomeViewModel
import br.com.samuel.barbershopapplication.utils.formatCurrency

@Composable
fun HomeScreen(
  homeViewModel: HomeViewModel = hiltViewModel(),
  navController: NavController

) {
  val servicesData = homeViewModel.serviceData
  val professionals = homeViewModel.professionals
  val specialties = homeViewModel.specialties
  val tabs = listOf("Serviços", "Profissionais", "Especializações", "Detalhes")
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  val isLoggedIn = homeViewModel.isLoggedIn
  LaunchedEffect(selectedTabIndex) {
    homeViewModel.verifyIsUserLoggedIn(navController = navController) //TODO: CRIAR OUTRO LAUCHED
    when (selectedTabIndex) {
      0 -> if (homeViewModel.serviceData.value.isEmpty()) homeViewModel.getAllServices()
      1 -> if (homeViewModel.professionals.value.isEmpty()) homeViewModel.getAllProfessionals()
      2 -> if (homeViewModel.specialties.value.isEmpty()) homeViewModel.getAllSpecialties()
    }
  }

  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = { /*TODO VOLTAR NAVIGATION*/ }) {
        Icon(
          painter = painterResource(R.drawable.ic_back_24),
          contentDescription = "Back"
        )
      }
      Text(
        text = "BarberShop",
      )
      IconButton(onClick = {/*TODO TELA DE MAPA*/ }) {
        Icon(
          painter = painterResource(R.drawable.ic_location_24),
          contentDescription = "Back"
        )
      }
    }
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
    Column(modifier = Modifier.padding()) {
      when (selectedTabIndex) {
        0 -> ServicesTab(services = servicesData.value)
        1 -> ProfessionalsTab(professionals = professionals.value)
        2 -> SpecialtiesTab(specialties = specialties.value)
      }
    }

  }
}

@Composable
fun ServicesTab(services: List<ApiServiceResponse>) {
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
            Button(
              modifier = Modifier,
              shape = RoundedCornerShape(4.dp),
              contentPadding = PaddingValues(4.dp),
              onClick = {}) {
              Text(
                text = "Agendar",
              )
            }
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
              Button(
                modifier = Modifier,
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                onClick = {}) {
                Text(
                  text = "Ver mais",
                )
              }
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
              Button(
                modifier = Modifier,
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                onClick = {}) {
                Text(
                  text = "Ver mais",
                )
              }
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
private fun HomeScreenPreview() {
  val navController = rememberNavController()
  val apiServiceServiceMock = ApiServiceServiceMock()
  val apiProfessionalServiceMock = ApiProfessionalServiceMock()
  val apiSpecialtyServiceMock = ApiSpecialtyServiceMock()
  val sharedPrefsServiceMock = SharedPrefsServiceMock()

  val homeViewModel = HomeViewModel(
    sharedPrefsServiceMock,
    apiProfessionalServiceMock,
    apiServiceServiceMock,
    apiSpecialtyServiceMock
  )

  HomeScreen(homeViewModel, navController)
}