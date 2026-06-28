package com.edu.ucne.dragonballzplanets.Presentation.Navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.edu.ucne.dragonballzplanets.Presentation.Detail.Character.CharacterDetailScreen
import com.edu.ucne.dragonballzplanets.Presentation.Detail.Character.DetailCharacterViewModel
import com.edu.ucne.dragonballzplanets.Presentation.Detail.Planet.PlanetDetailScreen
import com.edu.ucne.dragonballzplanets.Presentation.List.Character.CharacterListScreen
import com.edu.ucne.dragonballzplanets.Presentation.List.Planet.PlanetListScreen


@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.PlanetList,
        modifier = Modifier.padding(paddingValues),

        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 3 },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 3 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable<Screen.PlanetList> {
            PlanetListScreen(
                onPlanetClick = { planetId ->
                    navHostController.navigate(Screen.PlanetDetail(id = planetId))
                }
            )
        }

        composable<Screen.PlanetDetail> {
            PlanetDetailScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.CharacterList> {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navHostController.navigate(Screen.CharacterDetail(id = characterId))
                }
            )
        }

        composable<Screen.CharacterDetail> {
            val viewModel: DetailCharacterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            CharacterDetailScreen(
                state = state,
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}