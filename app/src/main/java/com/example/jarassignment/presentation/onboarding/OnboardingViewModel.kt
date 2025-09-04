package com.example.jarassignment.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jarassignment.domain.usecase.GetOnboardingDataUseCase
import com.example.jarassignment.domain.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingDataUseCase: GetOnboardingDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingUiState>(OnboardingUiState.Loading)
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()
    private var isFirstTimeInit: Boolean = false

    fun startOnboardingFlow() {
        viewModelScope.launch {
            _uiState.value = OnboardingUiState.Loading
            when (val result = getOnboardingDataUseCase()) {
                is NetworkResult.Success -> {
                    val response = result.data
                    if (!isFirstTimeInit) {
                        isFirstTimeInit = true
                        // Show welcome screen
                        _uiState.value = OnboardingUiState.Welcome(response.data.manualBuyEducationData)
                        delay(1000)
                    }
                    // Show Blank Screen
                    _uiState.value = OnboardingUiState.Loading
                    delay(1000)
                    _uiState.value = OnboardingUiState.HeroCardAnimation(
                        data = response.data.manualBuyEducationData,
                        animationPhase = HeroAnimationPhase.IDLE
                    )
                }
                is NetworkResult.Error -> {
                    _uiState.value = OnboardingUiState.Error(
                        "Failed to load onboarding data: ${result.exception.message}"
                    )
                }
                is NetworkResult.Loading -> {
                    _uiState.value = OnboardingUiState.Loading
                }
            }
        }
    }

    fun retryLoad() {
        _uiState.value = OnboardingUiState.Loading
        startOnboardingFlow()
    }
}