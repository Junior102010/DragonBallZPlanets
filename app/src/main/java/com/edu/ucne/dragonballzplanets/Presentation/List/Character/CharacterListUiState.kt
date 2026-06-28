package com.edu.ucne.dragonballzplanets.Presentation.List.Character

import com.edu.ucne.dragonballzplanets.Domain.Model.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val character: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = ""
)