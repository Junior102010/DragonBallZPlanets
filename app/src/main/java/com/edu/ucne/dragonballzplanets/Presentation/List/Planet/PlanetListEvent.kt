package com.edu.ucne.dragonballzplanets.Presentation.List.Planet

sealed interface PlanetListEvent {
    data class UpdateFilters(val name: String, val isDestroyed: Boolean? = null) : PlanetListEvent
    data object Search: PlanetListEvent
}