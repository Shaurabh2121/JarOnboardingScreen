package com.example.jarassignment.domain.usecase

import com.example.jarassignment.domain.model.OnboardingResponse
import com.example.jarassignment.domain.repository.OnboardingRepository
import com.example.jarassignment.domain.util.NetworkResult
import javax.inject.Inject

class GetOnboardingDataUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    suspend operator fun invoke(): NetworkResult<OnboardingResponse> {
        return repository.getOnboardingData()
    }
}