package com.example.cartoonizeimage.di

import com.example.cartoonizeimage.data.remote.ApiService
import com.example.cartoonizeimage.data.repository.ApiRepoImpl
import com.example.cartoonizeimage.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(apiService: ApiService): ImageRepository {
        return ApiRepoImpl(apiService)
    }
}