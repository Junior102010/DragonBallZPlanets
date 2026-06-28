package com.edu.ucne.dragonballzplanets.Data.Remote.RemoteDataSource

import coil3.network.HttpException
import com.edu.ucne.dragonballzplanets.Data.Dto.CharacterDto
import com.edu.ucne.dragonballzplanets.Data.Dto.CharactersResponseDto
import com.edu.ucne.dragonballzplanets.Data.Remote.DragonBallApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
){
    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,

    ): Result<CharactersResponseDto>{
        try {
            val response = api.getCharacters(page, limit, name, gender, race)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        }catch (e: HttpException){
            return Result.failure(Exception("Error de Servidor", e))
        }catch (e: Exception){
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getCharacterDetail(id: Int): Result<CharacterDto>{
        try{
            val response = api.getCharacterDetail(id)
            if (!response.isSuccessful){
                return Result.failure(Exception("Error de red ${response.code()}"))

            }
            return Result.success(response.body()!!)
        }catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }
}