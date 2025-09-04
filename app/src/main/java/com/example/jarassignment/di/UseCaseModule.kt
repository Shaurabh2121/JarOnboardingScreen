package com.example.jarassignment.di

import com.example.jarassignment.domain.repository.OnboardingRepository
import com.example.jarassignment.domain.usecase.GetOnboardingDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetOnboardingDataUseCase(repository: OnboardingRepository): GetOnboardingDataUseCase {
        return GetOnboardingDataUseCase(repository)
    }
}