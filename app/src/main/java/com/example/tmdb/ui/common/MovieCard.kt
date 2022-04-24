package com.example.tmdb.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(image: Int, onMovieCardClick: () -> Unit, height: Dp, width: Dp) {
    Card(modifier = Modifier
        .height(height) //179
        .width(width), //122
        shape = RoundedCornerShape(15.dp),
        onClick = { onMovieCardClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(5.dp)) {
                Heart()
            }
        }
    }
}