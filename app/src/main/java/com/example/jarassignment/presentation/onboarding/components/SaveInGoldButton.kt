package com.example.jarassignment.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jarassignment.domain.model.SaveButtonCta

@Composable
fun SaveInGoldButton(modifier: Modifier, buttonCta: SaveButtonCta, lottieUrl: String,onClick: () -> Unit) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url(lottieUrl)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Row(
        modifier = modifier
            .padding(bottom = 51.dp)
            .background(
                Color(android.graphics.Color.parseColor(buttonCta.backgroundColor)),
                shape = RoundedCornerShape(31.dp)
            )
            .clickable { onClick() }
            .padding(start = 24.dp, end = 16.dp, top = 2.dp, bottom = 2.dp)
            .border(
                width = 1.dp,
                color = Color(android.graphics.Color.parseColor(buttonCta.strokeColor)),
                shape = RoundedCornerShape(31.dp)
            )
            .clip(shape = RoundedCornerShape(31.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = buttonCta.text,
            color = Color(android.graphics.Color.parseColor(buttonCta.textColor)),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(44.dp)
        )
    }
}