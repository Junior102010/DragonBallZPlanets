package com.edu.ucne.dragonballzplanets.Domain.Model

data class Planet(
    val  id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String
)