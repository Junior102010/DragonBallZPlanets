package com.edu.ucne.dragonballzplanets.Presentation.Detail.Character

import com.edu.ucne.dragonballzplanets.Domain.Model.Character

data class DetailCharacterUiState (
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)