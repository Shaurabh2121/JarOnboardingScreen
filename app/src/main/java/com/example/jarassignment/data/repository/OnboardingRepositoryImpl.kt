package com.example.jarassignment.data.repository

import com.example.jarassignment.data.api.OnboardingApiService
import com.example.jarassignment.data.mapper.OnboardingDataMapper
import com.example.jarassignment.domain.model.OnboardingResponse
import com.example.jarassignment.domain.repository.OnboardingRepository
import com.example.jarassignment.domain.util.NetworkResult
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val apiService: OnboardingApiService,
    private val mapper: OnboardingDataMapper
) : OnboardingRepository {
    override suspend fun getOnboardingData(): NetworkResult<OnboardingResponse> {
        return try {
            val apiResponse = apiService.getEducationMetadata()
            val domainModel = mapper.mapToDomainModel(apiResponse)
            NetworkResult.success(domainModel)
        } catch (e: Exception) {
            NetworkResult.error(e)
        }
    }
}