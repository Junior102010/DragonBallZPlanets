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
    val descripcion: String,
    val image: String,
    val maxKi: String,
){
    fun toDomain() = Character(
        id,name,ki,race,gender,descripcion,image,maxKi
    )
}