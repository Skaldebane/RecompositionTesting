package dev.skaldebane.test.recomposition.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets

private val DarkColorPalette = darkColors(
    primary = brandColorDark,
    primaryVariant = brandColorDark,
    secondary = accentColorDark,
    onPrimary = Color.White,
    onSecondary = Color.White,
    background = backgroundColorDark,
    surface = surfaceColorDark,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = brandColorLight,
    primaryVariant = brandColorLight,
    secondary = accentColorLight,
    onSecondary = Color.White,
    onPrimary = Color.White,
    background = backgroundColorLight,
    surface = surfaceColorLight,
    onSurface = Color.Black
)

@Composable
fun RecompositionTestingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideWindowInsets {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
