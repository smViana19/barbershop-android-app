package br.com.samuel.barbershopapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.samuel.barbershopapplication.ui.navigation.MainNavigation
import br.com.samuel.barbershopapplication.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    enableEdgeToEdge()
    setContent {
      AppTheme {
        MainNavigation()
      }
    }
  }
}
