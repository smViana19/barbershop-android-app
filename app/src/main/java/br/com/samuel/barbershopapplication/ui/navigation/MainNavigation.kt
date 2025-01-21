package br.com.samuel.barbershopapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsServiceImpl
import br.com.samuel.barbershopapplication.ui.screens.CalendarScreen
import br.com.samuel.barbershopapplication.ui.screens.HomeScreen
import br.com.samuel.barbershopapplication.ui.screens.LoginScreen
import br.com.samuel.barbershopapplication.ui.screens.RegisterScreen
import br.com.samuel.barbershopapplication.ui.screens.ScheduleScreen
import br.com.samuel.barbershopapplication.ui.viewmodels.HomeViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.LoginViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.RegisterViewModel


@Composable
fun MainNavigation() {
  val navController = rememberNavController()
  val registerViewModel: RegisterViewModel = hiltViewModel()
  val loginViewModel: LoginViewModel = hiltViewModel()
  val homeViewModel: HomeViewModel = hiltViewModel()
  val context = LocalContext.current
  val sharedPrefsService = SharedPrefsServiceImpl(context)
  NavHost(
    navController = navController,
    startDestination = NavigationScreens.HOME_SCREEN.name
  ) {
    composable(route = NavigationScreens.REGISTER_SCREEN.name) {
      RegisterScreen(registerViewModel, navController)
    }

    composable(route = NavigationScreens.LOGIN_SCREEN.name) {
      LoginScreen(loginViewModel, navController)
    }

    composable(route = NavigationScreens.HOME_SCREEN.name) {
      HomeScreen(homeViewModel, navController)
    }
    composable(
      route = "${NavigationScreens.SCHEDULE_SCREEN.name}?selectedDate={selectedDate}&serviceId={serviceId}",
      arguments = listOf(
        navArgument("selectedDate") {
          type = NavType.StringType
          defaultValue = ""
        },
        navArgument("serviceId") {
          type = NavType.IntType
          defaultValue = -1
        })
    ) { backStackEntry ->
      val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
      val serviceId = backStackEntry.arguments?.getInt("serviceId") ?: -1
      ScheduleScreen(selectedDate, serviceId, navController, sharedPrefsService)
    }

    composable(route = NavigationScreens.CALENDAR_SCREEN.name) {
      CalendarScreen(navController)
    }
  }
}