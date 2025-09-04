package com.example.jarassignment.di

import com.example.jarassignment.data.repository.OnboardingRepositoryImpl
import com.example.jarassignment.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(repository: OnboardingRepositoryImpl): OnboardingRepository
}