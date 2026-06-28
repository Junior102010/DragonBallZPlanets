package com.edu.ucne.dragonballzplanets.Domain.Repository

import com.edu.ucne.dragonballzplanets.Data.Remote.Resource
import com.edu.ucne.dragonballzplanets.Domain.Model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(
        page : Int,
        limit : Int,
        name: String?,
        gender : String?,
        race : String?
    ) : Flow<Resource<List<Character>>>

    fun getCharacterDetail(id:Int): Flow<Resource<Character>>
}