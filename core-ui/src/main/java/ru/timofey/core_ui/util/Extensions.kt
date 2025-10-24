package ru.timofey.core_ui.util

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun String.toColorOrDefault(default: Color): Color = try {
    Color(this.toColorInt())
} catch (_: Exception) {
    default
}
