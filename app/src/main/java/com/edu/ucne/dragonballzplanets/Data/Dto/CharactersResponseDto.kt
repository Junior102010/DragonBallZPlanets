package com.edu.ucne.dragonballzplanets.Data.Dto

import com.edu.ucne.dragonballzplanets.Domain.Model.Character
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponseDto(
    val items: List<CharacterDto>
)

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val id: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String,
){
    fun toDomain() = Character(
        id, name, ki, race, gender, description, image, maxKi
    )
}