package com.areyana.design_system

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class GradientColors(
    val primary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val tertiary: Color = Color.Unspecified,
    val neutral: Color = Color.Unspecified
)

val LocalGradientColors = staticCompositionLocalOf { GradientColors() }