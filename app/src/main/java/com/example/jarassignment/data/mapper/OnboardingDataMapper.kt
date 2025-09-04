package com.example.jarassignment.data.mapper

import com.example.jarassignment.domain.model.OnboardingResponse

interface OnboardingDataMapper {
    fun mapToDomainModel(apiResponse: com.example.jarassignment.data.api.model.OnboardingResponse): OnboardingResponse
}