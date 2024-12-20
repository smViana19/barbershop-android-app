package br.com.samuel.barbershopapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.ui.screens.ScheduleScreen
import br.com.samuel.barbershopapplication.ui.screens.CalendarScreen
import br.com.samuel.barbershopapplication.ui.screens.HomeScreen
import br.com.samuel.barbershopapplication.ui.screens.LoginScreen
import br.com.samuel.barbershopapplication.ui.screens.RegisterScreen
import br.com.samuel.barbershopapplication.ui.viewmodels.HomeViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.LoginViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.RegisterViewModel


@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val registerViewModel: RegisterViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
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

        composable(route = NavigationScreens.APPOINTMENT_SCREEN.name) {
            ScheduleScreen()
        }

        composable(route = NavigationScreens.CALENDAR_SCREEN.name) {
            CalendarScreen()
        }




        /**
         * Composable para passar como parametro
         */
//        composable(
//            route = "${NavigationScreens.LOGIN_SCREEN.name}/{userId}",
//            arguments = listOf(navArgument("userId") {type = NavType.IntType} )
//        ) { navBackEntry ->
//
//
//        }
    }
}