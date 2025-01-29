package br.com.samuel.barbershopapplication.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.samuel.barbershopapplication.R
import br.com.samuel.barbershopapplication.backendservices.sharedprefs.SharedPrefsServiceImpl
import br.com.samuel.barbershopapplication.ui.components.BottomNavItems
import br.com.samuel.barbershopapplication.ui.components.BottomNavbar
import br.com.samuel.barbershopapplication.ui.screens.AppointmentScreen
import br.com.samuel.barbershopapplication.ui.screens.CalendarScreen
import br.com.samuel.barbershopapplication.ui.screens.ServiceManagementScreen
import br.com.samuel.barbershopapplication.ui.screens.LoginScreen
import br.com.samuel.barbershopapplication.ui.screens.ProfileScreen
import br.com.samuel.barbershopapplication.ui.screens.RegisterScreen
import br.com.samuel.barbershopapplication.ui.screens.ScheduleScreen
import br.com.samuel.barbershopapplication.ui.viewmodels.AppointmentViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.ServiceManagementViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.LoginViewModel
import br.com.samuel.barbershopapplication.ui.viewmodels.RegisterViewModel


@Composable
fun MainNavigation() {
  val navController = rememberNavController()
  val registerViewModel: RegisterViewModel = hiltViewModel()
  val loginViewModel: LoginViewModel = hiltViewModel()
  val serviceManagementViewModel: ServiceManagementViewModel = hiltViewModel()
  val appointmentViewModel: AppointmentViewModel = hiltViewModel()
  val context = LocalContext.current
  val sharedPrefsService = SharedPrefsServiceImpl(context)

  val bottomNavItems = listOf(
    BottomNavItems(
      route = NavigationScreens.SERVICE_MANAGEMENT_SCREEN.name,
      icon = painterResource(R.drawable.ic_home_24),
      label = "Início"
    ),
    BottomNavItems(
      route = NavigationScreens.APPOINTMENT_SCREEN.name,
      icon = painterResource(R.drawable.ic_books_24),
      label = "Agendamentos"
    ),
    BottomNavItems(
      route = NavigationScreens.PROFILE_SCREEN.name,
      icon = painterResource(R.drawable.ic_person_24),
      label = "Meu perfil"
    ),

    )
  Scaffold(
    bottomBar = {
      when (navController.currentBackStackEntryAsState().value?.destination?.route) {
        NavigationScreens.SERVICE_MANAGEMENT_SCREEN.name -> BottomNavbar(
          navController = navController,
          items = bottomNavItems
        )

        else -> {}
      }
    },
    content = { innerPadding ->
      NavHost(
        navController = navController,
        startDestination = NavigationScreens.SERVICE_MANAGEMENT_SCREEN.name,
        modifier = Modifier.padding(innerPadding)
      ) {
        composable(
          route = NavigationScreens.REGISTER_SCREEN.name,
          enterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Left,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
          },
          exitTransition = {
            slideOutOfContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
          },
          popEnterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
          }
        ) {
          RegisterScreen(registerViewModel, navController)
        }

        composable(route = NavigationScreens.LOGIN_SCREEN.name) {
          LoginScreen(loginViewModel, navController)
        }

        composable(
          route = NavigationScreens.SERVICE_MANAGEMENT_SCREEN.name,
          enterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(300))
          },
          exitTransition = {
            slideOutOfContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Left,
              animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(300))
          },
          popEnterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(300))
          }
        ) {
          ServiceManagementScreen(serviceManagementViewModel, navController)
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
            }),
          enterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Left,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
          },
          exitTransition = {
            slideOutOfContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
          },
          popEnterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
          }
        ) { backStackEntry ->
          val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
          val serviceId = backStackEntry.arguments?.getInt("serviceId") ?: -1
          ScheduleScreen(selectedDate, serviceId, navController, sharedPrefsService)
        }

        composable(
          route = NavigationScreens.CALENDAR_SCREEN.name,
          enterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Down,
              animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
          },) {
          CalendarScreen(navController)
        }

        composable(
          route = NavigationScreens.APPOINTMENT_SCREEN.name,
          enterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Left,
              animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
          },
          exitTransition = {
            slideOutOfContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
          },
          popEnterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(500))
          }) {
          AppointmentScreen(appointmentViewModel, navController)
        }
        composable(
          route = NavigationScreens.PROFILE_SCREEN.name,
          enterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Left,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
          },
          exitTransition = {
            slideOutOfContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
          },
          popEnterTransition = {
            slideIntoContainer(
              towards = AnimatedContentTransitionScope.SlideDirection.Right,
              animationSpec = tween(500)
            ) + fadeIn(animationSpec = tween(500))
          }) {
          ProfileScreen(navController)
        }

      }
    }
  )

}
