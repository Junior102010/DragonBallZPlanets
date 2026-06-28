package com.edu.ucne.dragonballzplanets.Presentation.List.Character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.UseCase.Character.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(CharacterListUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: CharacterListEvent){
        when(event) {
            is CharacterListEvent.UpdateFilters -> _state.update {
                it.copy(
                    filterName = event.name,
                    filterGender = event.genter,
                    filterRace = event.race
                )
            }

            CharacterListEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters(){
        viewModelScope.launch {
            val current = _state.value

            getCharactersUseCase(
                name = current.filterName.takeIf { it.isNotBlank() },
                gender = current.filterGender.takeIf { it.isNotBlank() },
                race = current.filterRace.takeIf { it.isNotBlank() }
            ).collect { result ->

                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                character = result.data ?: emptyList()
                            )
                        }

                    is Resource.Error ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }

                }

            }

        }
    }
}