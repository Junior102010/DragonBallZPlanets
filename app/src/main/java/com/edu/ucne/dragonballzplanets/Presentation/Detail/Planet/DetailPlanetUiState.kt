package com.edu.ucne.dragonballzplanets.Presentation.Detail.Planet

import com.edu.ucne.dragonballzplanets.Domain.Model.Planet

data class DetailPlanetUiState (
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)