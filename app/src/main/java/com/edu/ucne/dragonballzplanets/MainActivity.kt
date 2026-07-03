package com.edu.ucne.dragonballzplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.rememberNavBackStack
import com.edu.ucne.dragonballzplanets.Presentation.Navigation.AppNavDisplay
import com.edu.ucne.dragonballzplanets.Presentation.Navigation.Screen
import com.edu.ucne.dragonballzplanets.ui.theme.DragonBallZPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragonBallZPlanetsTheme {
                val backStack = rememberNavBackStack(Screen.PlanetList)
                val items = listOf(
                    TopLevelRoute("Planetas", Screen.PlanetList, Icons.Default.Public),
                    TopLevelRoute("Personajes", Screen.CharacterList, Icons.Default.Person)
                )

                Scaffold(
                    bottomBar = {
                        val currentDestination = backStack.lastOrNull()

                        val isDetail = currentDestination is Screen.PlanetDetail ||
                                currentDestination is Screen.CharacterDetail

                        if (!isDetail) {
                            NavigationBar {
                                items.forEach { item ->
                                    NavigationBarItem(
                                        icon = { Icon(item.icono, contentDescription = item.nombre) },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination == item.ruta,
                                        onClick = {
                                            if (currentDestination != item.ruta) {
                                                backStack.clear()
                                                backStack.add(item.ruta)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    AppNavDisplay(
                        backStack = backStack,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

data class TopLevelRoute<T : Screen>(
    val nombre: String,
    val ruta: T,
    val icono: ImageVector
)
