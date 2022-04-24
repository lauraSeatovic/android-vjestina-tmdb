package com.example.tmdb.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.DeepBlue

@Preview
@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(DeepBlue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
            modifier = Modifier
                .width(143.dp)
                .height(45.dp)
                .align(Alignment.BottomCenter)
        )
    }
}