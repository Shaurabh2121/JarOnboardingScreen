package com.example.jarassignment.domain.repository

import com.example.jarassignment.domain.model.OnboardingResponse
import com.example.jarassignment.domain.util.NetworkResult

interface OnboardingRepository {
    suspend fun getOnboardingData(): NetworkResult<OnboardingResponse>
}