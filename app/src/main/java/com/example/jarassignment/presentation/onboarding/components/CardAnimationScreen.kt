package com.example.jarassignment.presentation.onboarding.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.jarassignment.domain.model.ManualBuyEducationData
import com.example.jarassignment.presentation.onboarding.AnimationTiltState
import com.example.jarassignment.presentation.onboarding.HeroAnimationPhase
import kotlinx.coroutines.delay

@Composable
fun CardAnimationScreen(
    data: ManualBuyEducationData,
    onClick: () -> Unit
) {
    val cardCount = data.educationCardList.size

    var selectedCardIndex by remember { mutableStateOf<Int?>(null) }
    var animationDone by remember { mutableStateOf(false) }

    val cardPhases = remember {
        mutableStateListOf<HeroAnimationPhase>().apply {
            repeat(cardCount) { add(HeroAnimationPhase.IDLE) }
        }
    }
    val cardTilts = remember {
        mutableStateListOf<AnimationTiltState>().apply {
            repeat(cardCount) { add(AnimationTiltState.NONE) }
        }
    }

    var activeIndex by remember { mutableStateOf(0) }

    fun tiltDirectionForIndex(state: AnimationTiltState) =
        if (state == AnimationTiltState.LEFT) AnimationTiltState.RIGHT else AnimationTiltState.LEFT

    // Sequential animation loop
    LaunchedEffect(Unit) {
        delay(100)
        for (i in 0 until cardCount) {
            activeIndex = i
            // Expand
            cardPhases[i] = HeroAnimationPhase.EXPANDING
            if (i != 0) {
                cardTilts[i] = tiltDirectionForIndex(cardTilts[i - 1])
            }
            delay(data.expandCardStayInterval.toLong())
            // Collapse
            if (i < cardCount - 1) {
                cardPhases[i] = HeroAnimationPhase.COLLAPSING
                if (i == 0) {
                    cardTilts[i] =  AnimationTiltState.LEFT
                }
            } else {
                cardPhases[i] = HeroAnimationPhase.DEFAULT_OPEN
            }
        }
        animationDone = true
    }

    // Background animation
    val bgIndex = if (!animationDone) activeIndex else (selectedCardIndex ?: activeIndex)
    val bgCard = data.educationCardList[bgIndex]

    val backgroundColor by animateColorAsState(
        targetValue = Color(android.graphics.Color.parseColor(bgCard.backgroundColor)),
        label = "bgColorAnim"
    )
    val startGradient by animateColorAsState(
        targetValue = Color(android.graphics.Color.parseColor(bgCard.startGradient)),
        label = "startGradAnim"
    )
    val endGradient by animateColorAsState(
        targetValue = Color(android.graphics.Color.parseColor(bgCard.endGradient)),
        label = "endGradAnim"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .background(
                    brush = Brush.verticalGradient(listOf(startGradient, endGradient))
                )
        ) {
            // Top toolbar
            OnboardingTopBar("Onboarding", modifier = Modifier.fillMaxWidth()){}
            // Cards
            cardPhases.forEachIndexed { index, _ ->
                val phaseForIndex = when {
                    !animationDone -> cardPhases[index]
                    selectedCardIndex == null -> cardPhases[index]
                    selectedCardIndex == index -> HeroAnimationPhase.DEFAULT_OPEN
                    else -> HeroAnimationPhase.COLLAPSING
                }
                val isClickable = animationDone &&
                        (selectedCardIndex == null || selectedCardIndex != index)

                AnimationCard(
                    card = data.educationCardList[index],
                    animationPhase = phaseForIndex,
                    currentHeroIndex = index,
                    tiltState = cardTilts[index],
                    collapseCardTiltInterval = data.collapseCardTiltInterval,
                    collapseExpandIntroInterval = data.collapseExpandIntroInterval,
                    expandCardStayInterval = data.expandCardStayInterval,
                    bottomToCenterTranslationInterval = data.bottomToCenterTranslationInterval,
                    cardCount = cardCount,
                    isClickable = isClickable,
                    onClick = {
                        if (animationDone) {
                            selectedCardIndex = index
                        }
                    }
                )
            }
        }

        // CTA button
        if (animationDone) {
            SaveInGoldButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                buttonCta = data.saveButtonCta,
                lottieUrl = data.ctaLottie,
                onClick = onClick
            )
        }
    }
}
