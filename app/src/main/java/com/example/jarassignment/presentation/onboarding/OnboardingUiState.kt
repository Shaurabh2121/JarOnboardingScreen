package com.example.jarassignment.presentation.onboarding

import com.example.jarassignment.domain.model.ManualBuyEducationData

// UI State definitions
sealed class OnboardingUiState {
    object Loading : OnboardingUiState()
    data class Welcome(val data: ManualBuyEducationData) : OnboardingUiState()
    data class HeroCardAnimation(
        val data: ManualBuyEducationData,
        val animationPhase: HeroAnimationPhase
    ) : OnboardingUiState()

    data class Error(val message: String) : OnboardingUiState()
}

enum class HeroAnimationPhase {
    EXPANDING,
    COLLAPSING,
    DEFAULT_OPEN,
    IDLE
}

enum class AnimationTiltState {
    NONE,
    LEFT,
    RIGHT
}