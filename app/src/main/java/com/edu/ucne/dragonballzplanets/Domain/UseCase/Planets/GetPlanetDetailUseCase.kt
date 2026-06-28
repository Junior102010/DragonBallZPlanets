package com.edu.ucne.dragonballzplanets.Domain.UseCase.Planets

import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.Model.Planet
import com.edu.ucne.dragonballzplanets.Domain.Repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
){
    operator fun invoke(id: Int): Flow<Resource<Planet>> {
        return repository.getPlanetDetail(id)
    }
}