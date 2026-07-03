package com.edu.ucne.dragonballzplanets.Presentation.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.edu.ucne.dragonballzplanets.Presentation.Detail.Character.CharacterDetailScreen
import com.edu.ucne.dragonballzplanets.Presentation.Detail.Character.DetailCharacterViewModel
import com.edu.ucne.dragonballzplanets.Presentation.Detail.Planet.PlanetDetailScreen
import com.edu.ucne.dragonballzplanets.Presentation.List.Character.CharacterListScreen
import com.edu.ucne.dragonballzplanets.Presentation.List.Planet.PlanetListScreen
import androidx.navigation3.runtime.entryProvider


@Composable
fun AppNavDisplay(
    backStack: NavBackStack<NavKey>,
    paddingValues: PaddingValues
) {
    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(paddingValues),
        entryProvider = entryProvider{
            entry<Screen.PlanetList> {
                PlanetListScreen(
                    onPlanetClick = { planetId ->
                        backStack.add(Screen.PlanetDetail(planetId))
                    }
                )
            }

            entry<Screen.PlanetDetail> { key ->
                PlanetDetailScreen(
                    planetId = key.id,
                    onBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }

            entry<Screen.CharacterList> {
                CharacterListScreen(
                    onCharacterClick = { characterId ->
                        backStack.add(Screen.CharacterDetail(characterId))
                    }
                )
            }

            
            entry<Screen.CharacterDetail> { key ->
                val viewModel: DetailCharacterViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                CharacterDetailScreen(
                    characterId = key.id,
                    state = state,
                    onBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    },
                )
            }
        }

    )
}