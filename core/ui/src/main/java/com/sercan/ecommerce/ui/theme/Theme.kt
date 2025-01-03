package com.sercan.ecommerce.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF3700B3),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    
    // Özel renkler
    surfaceVariant = Color(0xFF2D2D2D),        // Bottom menu background
    onSurfaceVariant = Color.White,            // Bottom menu icon & text
    secondaryContainer = Color(0xFF1E1E1E),    // Product card background
    onSecondaryContainer = Color.White,        // Product card text
    tertiaryContainer = Color(0xFF2D2D2D),     // Banner background
    onTertiaryContainer = Color.White          // Banner text
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF3700B3),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    
    // Özel renkler
    surfaceVariant = Color.White,              // Bottom menu background
    onSurfaceVariant = Color(0xFF6200EE),      // Bottom menu icon & text
    secondaryContainer = Color(0xFFF5F5F5),    // Product card background
    onSecondaryContainer = Color.Black,        // Product card text
    tertiaryContainer = Color(0xFFF5F5F5),     // Banner background
    onTertiaryContainer = Color.Black          // Banner text
)

@Composable
fun EcommerceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeManager: ThemeManager,
    content: @Composable () -> Unit
) {
    val isDarkMode by themeManager.isDarkMode.collectAsState(initial = darkTheme)
    
    val colorScheme = when {
        isDarkMode -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkMode
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}