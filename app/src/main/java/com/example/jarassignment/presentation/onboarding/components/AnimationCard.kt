package com.example.jarassignment.presentation.onboarding.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jarassignment.domain.model.EducationCard
import com.example.jarassignment.presentation.onboarding.AnimationTiltState
import com.example.jarassignment.presentation.onboarding.HeroAnimationPhase
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationCard(
    card: EducationCard,
    animationPhase: HeroAnimationPhase,
    currentHeroIndex: Int,
    tiltState: AnimationTiltState,
    collapseCardTiltInterval: Int,
    collapseExpandIntroInterval: Int,
    expandCardStayInterval: Int,
    bottomToCenterTranslationInterval: Int,
    cardCount: Int,
    isClickable: Boolean,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenHeightPx = remember(configuration, density) {
        with(density) { configuration.screenHeightDp.dp.toPx() }
    }
    val collapsedItemSizePx = remember(currentHeroIndex, density) {
        currentHeroIndex * with(density) { 84.dp.toPx() }
    }
    val positionCenter = remember(collapsedItemSizePx, density) {
        with(density) { 132.dp.toPx() } + collapsedItemSizePx / 2
    }
    val positionTop = 0f
    val rotationZ = remember { Animatable(0f) }

    // Handle tilt animations
    LaunchedEffect(tiltState) {
        when (tiltState) {
            AnimationTiltState.LEFT -> rotationZ.tilt(
                direction = -6f,
                interval = collapseCardTiltInterval,
                isLastCard = currentHeroIndex == cardCount - 1,
                isFristCard = currentHeroIndex == 0,
                expandCardStayInterval = expandCardStayInterval,
                collapseExpandIntroInterval = collapseExpandIntroInterval
            )

            AnimationTiltState.RIGHT -> rotationZ.tilt(
                direction = 6f,
                interval = collapseCardTiltInterval,
                isLastCard = currentHeroIndex == cardCount - 1,
                isFristCard = currentHeroIndex == 0,
                expandCardStayInterval = expandCardStayInterval,
                collapseExpandIntroInterval = collapseExpandIntroInterval
            )

            else -> {}
        }
    }

    // Y offset animation
    val targetOffset = when (animationPhase) {
        HeroAnimationPhase.COLLAPSING,
        HeroAnimationPhase.DEFAULT_OPEN -> positionTop
        HeroAnimationPhase.EXPANDING -> positionCenter
        else -> screenHeightPx
    }

    val offsetY by animateFloatAsState(
        targetValue = targetOffset,
        animationSpec = tween(
            durationMillis =
            if (animationPhase == HeroAnimationPhase.COLLAPSING) collapseExpandIntroInterval else bottomToCenterTranslationInterval
        ),
        label = "offsetAnim"
    )

    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .wrapContentSize()
            .graphicsLayer(
                rotationZ = rotationZ.value,
                translationY = offsetY
            )
            .clickable(
                enabled = isClickable,
                onClick = onClick
            ),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(android.graphics.Color.parseColor(card.strokeStartColor)),
                    Color(android.graphics.Color.parseColor(card.strokeEndColor))
                )
            )
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(card.backgroundColor))
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExpandableImage(
                    expanded = animationPhase == HeroAnimationPhase.EXPANDING ||
                            animationPhase == HeroAnimationPhase.DEFAULT_OPEN,
                    imageUrl = card.image,
                    collapseExpandIntroInterval = collapseExpandIntroInterval
                )
                if (animationPhase == HeroAnimationPhase.COLLAPSING) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        text = card.collapsedStateText,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = "KeyboardArrowDown",
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
            if (animationPhase == HeroAnimationPhase.EXPANDING ||
                animationPhase == HeroAnimationPhase.DEFAULT_OPEN
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                AnimatedContent(
                    targetState = card.expandStateText,
                    transitionSpec = {
                        slideInVertically { it } + fadeIn() with
                                slideOutVertically { -it } + fadeOut()
                    }
                ) { text ->
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandableImage(
    expanded: Boolean,
    imageUrl: String,
    collapseExpandIntroInterval: Int
) {
    val configuration = LocalConfiguration.current
    val width by animateDpAsState(
        targetValue = if (expanded) configuration.screenWidthDp.dp else 31.34.dp,
        label = "widthAnim",
        animationSpec = tween(collapseExpandIntroInterval)
    )
    val height by animateDpAsState(
        targetValue = if (expanded) 340.dp else 36.dp,
        label = "heightAnim",
        animationSpec = tween(collapseExpandIntroInterval)
    )

    Box(
        modifier = Modifier
            .size(width, height)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

// Helper extension for tilt animations
private suspend fun Animatable<Float, AnimationVector1D>.tilt(
    direction: Float,
    interval: Int,
    isLastCard: Boolean,
    isFristCard: Boolean,
    expandCardStayInterval: Int,
    collapseExpandIntroInterval: Int
    ) {
    animateTo(direction, tween(interval))
    delay(if (isLastCard || isFristCard) collapseExpandIntroInterval.toLong() else expandCardStayInterval.toLong())
    animateTo(0f, tween(interval))
}

