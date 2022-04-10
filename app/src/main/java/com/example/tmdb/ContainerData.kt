package com.example.tmdb

import androidx.compose.ui.graphics.painter.Painter

class ContainerData(
    val title : String,
    val buttons : List<ButtonData>,
    val images : List<List<Painter>>
)
