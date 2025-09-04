package com.example.jarassignment.di

import com.example.jarassignment.data.mapper.OnboardingDataMapper
import com.example.jarassignment.data.mapper.OnboardingDataMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindOnboardingDataMapper(mapper: OnboardingDataMapperImpl): OnboardingDataMapper
}