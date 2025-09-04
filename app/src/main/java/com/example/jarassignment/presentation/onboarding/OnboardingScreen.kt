package com.example.jarassignment.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jarassignment.presentation.onboarding.components.ErrorScreen
import com.example.jarassignment.presentation.onboarding.components.CardAnimationScreen
import com.example.jarassignment.presentation.onboarding.components.WelcomeScreen

@Composable
fun OnboardingScreen(onClick: () -> Unit) {
    val viewModel: OnboardingViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.startOnboardingFlow()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF201929))
    ) {
        when (val currentState = uiState) {
            is OnboardingUiState.Loading -> {}
            is OnboardingUiState.Welcome -> {
                WelcomeScreen(
                    title = currentState.data.introTitle,
                    subtitle = currentState.data.introSubtitle,
                    subtitleIcon = currentState.data.introSubtitleIcon
                )
            }
            is OnboardingUiState.HeroCardAnimation -> {
                CardAnimationScreen(
                    data = currentState.data
                ) {
                    onClick()
                }
            }
            is OnboardingUiState.Error -> {
                ErrorScreen(
                    message = currentState.message,
                    onRetry = { viewModel.retryLoad() }
                )
            }
        }
    }
}