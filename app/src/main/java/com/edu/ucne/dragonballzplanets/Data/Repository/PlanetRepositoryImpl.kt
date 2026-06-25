package com.edu.ucne.dragonballzplanets.Data.Repository

import com.edu.ucne.dragonballzplanets.Data.Dto.PlanetDto
import com.edu.ucne.dragonballzplanets.Data.Remote.RemoteDataSource.PlanetRemoteDataSource
import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.Model.Planet
import com.edu.ucne.dragonballzplanets.Domain.Repository.PlanetRepository
import com.squareup.moshi.JsonClass
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planet>>> = flow {

        emit(Resource.Loading())

        val response = remoteDataSource.getPlanets(page, limit, name, isDestroyed)

        response.onSuccess { planetsResponse ->

            val planetasDominio = planetsResponse.items.map { it.toDomain() }
            emit(Resource.Success(planetasDominio))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener planetas"))
        }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {

        emit(Resource.Loading())

        val response = remoteDataSource.getPlanetDetail(id)

        response.onSuccess { planetDto ->

            emit(Resource.Success(planetDto.toDomain()))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener el detalle"))
        }
    }
}