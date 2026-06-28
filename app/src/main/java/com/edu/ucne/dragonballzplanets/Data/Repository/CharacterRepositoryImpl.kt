package com.edu.ucne.dragonballzplanets.Data.Repository

import com.edu.ucne.dragonballzplanets.Data.Remote.RemoteDataSource.CharacterRemoteDataSource
import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.Model.Character
import com.edu.ucne.dragonballzplanets.Domain.Repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getCharacters(page, limit, name, gender, race)
        response.onSuccess { characterResponse ->
            emit(Resource.Success(characterResponse.items.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getCharacterDetail(id)
        response.onSuccess { characterDto ->
            emit(Resource.Success(characterDto.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }
}