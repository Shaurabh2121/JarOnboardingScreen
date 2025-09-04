package com.example.jarassignment.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF272239),
    secondary = Color(0xFFFDF3D6),
    tertiary = Color(0xFF992D7E),
    background = Color.Black,
    surface = TextDarkSurface200,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = TextDarkSurface200,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF272239),
    secondary = Color(0xFFFDF3D6),
    tertiary = Color(0xFF992D7E),
    background = Color.Black,
    surface = TextDarkSurface200,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = TextDarkSurface200,
    onSurface = Color.White,
)

@Composable
fun OnboardingAnimationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme // Always use dark theme for this design

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}