package com.edu.ucne.dragonballzplanets.Presentation.List.Character

sealed interface CharacterListEvent{
    data class UpdateFilters(
        val name: String,
        val genter: String,
        val race: String
    ) : CharacterListEvent

    data object Search : CharacterListEvent
}