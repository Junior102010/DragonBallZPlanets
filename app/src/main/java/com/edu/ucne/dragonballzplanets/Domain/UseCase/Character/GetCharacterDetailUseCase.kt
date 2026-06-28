package com.edu.ucne.dragonballzplanets.Domain.UseCase.Character

import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.Model.Character
import com.edu.ucne.dragonballzplanets.Domain.Model.Planet
import com.edu.ucne.dragonballzplanets.Domain.Repository.CharacterRepository
import com.edu.ucne.dragonballzplanets.Domain.Repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(id: Int): Flow<Resource<Character>> {
        return repository.getCharacterDetail(id)
    }
}