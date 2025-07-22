package com.valorantinfo.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ValorantRed,
    primaryVariant = ValorantRedDark,
    secondary = ValorantGold,
    background = ValorantBlue,
    surface = ValorantGray,
    onPrimary = Color.White,
    onSecondary = ValorantBlue,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(
    primary = ValorantRed,
    primaryVariant = ValorantRedDark,
    secondary = ValorantGold,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = ValorantBlue,
    onBackground = ValorantBlue,
    onSurface = ValorantBlue,
)

@Composable
fun ValorantInfoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
