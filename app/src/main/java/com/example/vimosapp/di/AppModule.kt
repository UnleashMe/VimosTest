package com.example.vimosapp.di

import com.example.vimosapp.data.remote.dto.VimosService
import com.example.vimosapp.data.repository.VimosRepositoryImpl
import com.example.vimosapp.domain.repository.VimosRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesVimosService(): VimosService {
        return Retrofit.Builder()
            .baseUrl("https://vimos.ru:1457/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )

            .build()
            .create(VimosService::class.java)
    }

    @Provides
    @Singleton
    fun providesVimosRepository(vimosService: VimosService): VimosRepository {
        return VimosRepositoryImpl(vimosService)
    }
}