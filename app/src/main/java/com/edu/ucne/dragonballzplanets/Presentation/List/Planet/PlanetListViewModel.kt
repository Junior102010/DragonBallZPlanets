package com.edu.ucne.dragonballzplanets.Presentation.List.Planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.Repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val repository: PlanetRepository
): ViewModel(){
    private val _state = MutableStateFlow(PlanetListUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: PlanetListEvent){
        when(event){
            is PlanetListEvent.UpdateFilters -> _state.update {
                it.copy(filterName = event.name, filterIsDestroyed = event.isDestroyed)
            }
            PlanetListEvent.Search -> loadPlanets()
        }
    }

    private fun loadPlanets(){
        viewModelScope.launch {
            val current = _state.value

            repository.getPlanets(
                page = 1,
                limit = 50,
                name =  current.filterName.takeIf{it.isNotBlank()},
                isDestroyed = current.filterIsDestroyed
            ).collect { result ->
                when(result){
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}