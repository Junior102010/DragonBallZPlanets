package com.edu.ucne.dragonballzplanets.Data.Remote

import com.edu.ucne.dragonballzplanets.Data.Dto.CharacterDto
import com.edu.ucne.dragonballzplanets.Data.Dto.CharactersResponseDto
import com.edu.ucne.dragonballzplanets.Data.Dto.PlanetDto
import com.edu.ucne.dragonballzplanets.Data.Dto.PlanetsResponseDto
import com.edu.ucne.dragonballzplanets.Domain.Model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApi {
    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?,
        @Query("isDestroyed") isDestroyed: Boolean?
    ): Response<PlanetsResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<PlanetDto>



    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?,
        @Query("gender") gender: String?,
        @Query("race") race: String?,
    ): Response<CharactersResponseDto>

    @GET("characters")
    suspend fun searchCharacters(
        @Query("name") name: String
    ): Response<List<CharacterDto>>

    @GET("characters/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): Response<CharacterDto>
}