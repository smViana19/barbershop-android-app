package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.samuel.barbershopapplication.R

data class BottomNavItems(
  val route: String,
  val icon: Painter, //TODO: VER QUAL E OU E COMPOSALBE OU UNIT
  val label: String,
)



@Composable
fun BottomNavbar(
  navController: NavController,
  items: List<BottomNavItems>
) {
  val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
  NavigationBar(
    containerColor = Color(0xFF0F172A),
    contentColor = Color.White,
    tonalElevation = 5.dp,
    modifier = Modifier
      .fillMaxWidth()
      .height(60.dp)
  ) {
    items.forEach { item ->
      NavigationBarItem(
        modifier = Modifier
          .padding(0.dp),
        selected = currentRoute == item.route,
        onClick = {
          if (currentRoute != item.route) {
            navController.navigate(item.route)
          }
        },
        icon = {
          Icon(
            painter = item.icon,
            contentDescription = item.label,
            modifier = Modifier.padding(end = 4.dp)
          )
        },
        label = {
          Text(
            text = item.label,
            color = if (currentRoute == item.route) Color(0xFFE9B208) else Color.White,
            fontSize = 12.sp
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = Color(0xFFE9B208),
          unselectedIconColor = Color.White,
          selectedTextColor = Color(0xFFE9B208),
          unselectedTextColor = Color.White,
          indicatorColor = Color.Transparent
        )
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavbarPreview() {
  val navController = rememberNavController()
  val items = listOf(
    BottomNavItems(
      route = "inicio",
      icon = painterResource(R.drawable.ic_home_24),
      label = "Inicio"
    ),
    BottomNavItems(
      route = "agendamentos",
      icon = painterResource(R.drawable.ic_home_24),
      label = "Agendamentos"
    ),
    BottomNavItems(
      route = "perfil",
      icon = painterResource(R.drawable.ic_home_24),
      label = "Perfil"
    )
  )
  BottomNavbar(navController, items)
}