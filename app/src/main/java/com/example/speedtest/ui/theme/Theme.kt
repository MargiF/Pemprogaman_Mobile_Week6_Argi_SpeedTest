package com.example.speedtest.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * ═══════════════════════════════════════════════════════════════
 *  Theme.kt — Material 3 Pink Theme untuk Speed Test App
 * ═══════════════════════════════════════════════════════════════
 */

private val DarkColorScheme = darkColorScheme(
    primary = PinkPrimary,
    onPrimary = Color.White,
    primaryContainer = PinkPrimaryDark,
    secondary = RoseSecondary,
    onSecondary = Color.White,
    secondaryContainer = RoseDark,
    background = PinkDarkBackground,
    onBackground = TextOnDark,
    surface = PinkDarkSurface,
    onSurface = TextOnDark,
    surfaceVariant = PinkDarkSurfaceVariant,
    onSurfaceVariant = TextOnDarkSecondary,
    outline = PinkDarkSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = PinkPrimaryDark,
    onPrimary = Color.White,
    primaryContainer = PinkLight,
    secondary = RoseSecondary,
    onSecondary = Color.White,
    secondaryContainer = RoseLight,
    background = PinkLightBackground,
    onBackground = TextOnLight,
    surface = PinkLightSurface,
    onSurface = TextOnLight,
    surfaceVariant = PinkLightSurfaceVariant,
    onSurfaceVariant = TextOnLightSecondary,
    outline = PinkLightSurfaceVariant
)

@Composable
fun SpeedTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic Color dimatikan agar tetap menggunakan Pink Palette
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Set status bar color sesuai theme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SpeedTestTypography,
        content = content
    )
}
