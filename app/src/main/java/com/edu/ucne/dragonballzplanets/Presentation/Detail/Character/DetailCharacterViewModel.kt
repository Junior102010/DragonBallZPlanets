package com.edu.ucne.dragonballzplanets.Presentation.Detail.Character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.UseCase.Character.GetCharacterDetailUseCase
import com.edu.ucne.dragonballzplanets.Presentation.Navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailCharacterUiState())
    val state = _state.asStateFlow()



    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterDetailUseCase(id).collect{  result->
                when (result ) {
                    is Resource.Loading ->_state.update {it.copy(isLoading = true)}
                    is Resource.Success -> _state.update {it.copy(isLoading = false,character = result.data)}
                    is Resource.Error -> _state.update {
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