package com.edu.ucne.dragonballzplanets.Data.Remote.RemoteDataSource

import coil3.network.HttpException
import com.edu.ucne.dragonballzplanets.Data.Dto.PlanetDto
import com.edu.ucne.dragonballzplanets.Data.Dto.PlanetsResponseDto
import com.edu.ucne.dragonballzplanets.Data.Remote.DragonBallApi
import jakarta.inject.Inject

class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
){
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Result<PlanetsResponseDto>{
        return try {
            val response = api.getPlanets(page, limit, name, isDestroyed)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {

            if (!name.isNullOrBlank()) {
                try {
                    val alternativeResponse = api.getPlanets(1, 50, null, null)
                    if (alternativeResponse.isSuccessful && alternativeResponse.body() != null) {
                        val filteredList = alternativeResponse.body()!!.items.filter {
                            it.name.contains(name, ignoreCase = true)
                        }
                        Result.success(PlanetsResponseDto(filteredList))
                    } else {
                        Result.failure(Exception("Error al filtrar localmente."))
                    }
                } catch (nestedException: Exception) {
                    Result.failure(Exception("Error en filtro: ${nestedException.localizedMessage}"))
                }
            } else {
                Result.failure(Exception("Error desconocido", e))
            }
        }
    }
    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}