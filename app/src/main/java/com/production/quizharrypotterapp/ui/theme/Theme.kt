package com.production.quizharrypotterapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RavenclawDarkBlue,
    onPrimary = RavenclawGold,
    secondary = RavenclawGold,
    tertiary = RavenclawGold
)

private val LightColorScheme = lightColorScheme(
    primary = GriffindorRed,
    secondary = GriffindorYellow,
    tertiary = GriffindorGold,
    surface = Color(0xFFFFFBFE),
    onPrimary = GriffindorYellow,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun QuizAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}