package com.example.tmdb

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val route: String
) {
    object Details : Screen(
        route = "screen"
    )
}
