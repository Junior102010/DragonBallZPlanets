package com.edu.ucne.dragonballzplanets.Presentation.List

import com.edu.ucne.dragonballzplanets.Domain.Model.Planet

data class PlanetListUiState(
    val  isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterIsDestroyed: Boolean? = null
)