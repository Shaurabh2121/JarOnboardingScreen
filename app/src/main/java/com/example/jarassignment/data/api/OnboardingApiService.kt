package com.example.jarassignment.data.api

import com.example.jarassignment.data.api.model.OnboardingResponse
import retrofit2.http.GET

interface OnboardingApiService {
    @GET("_assets/shared/education-metadata.json")
    suspend fun getEducationMetadata(): OnboardingResponse
}