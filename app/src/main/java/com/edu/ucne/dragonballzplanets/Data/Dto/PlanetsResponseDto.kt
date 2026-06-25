package com.edu.ucne.dragonballzplanets.Data.Dto

import com.edu.ucne.dragonballzplanets.Domain.Model.Planet
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PlanetsResponseDto(
    val items: List<PlanetDto>
)

@JsonClass(generateAdapter = true)
data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
){
    fun toDomain() = Planet(
        id,
        name,
        isDestroyed,
        description,
        image
    )
}