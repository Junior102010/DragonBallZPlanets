package com.edu.ucne.dragonballzplanets.Di

import com.edu.ucne.dragonballzplanets.Data.Remote.DragonBallApi
import com.edu.ucne.dragonballzplanets.Data.Remote.RemoteDataSource.PlanetRemoteDataSource
import com.edu.ucne.dragonballzplanets.Data.Repository.PlanetRepositoryImpl
import com.edu.ucne.dragonballzplanets.Domain.Repository.PlanetRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule{

    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi{
        return Retrofit
            .Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(DragonBallApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(planetRemoteDataSource: PlanetRemoteDataSource): PlanetRepository {
        return PlanetRepositoryImpl(planetRemoteDataSource)
    }
}