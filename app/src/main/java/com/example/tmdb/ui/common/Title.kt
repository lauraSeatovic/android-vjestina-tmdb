package com.example.tmdb.ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tmdb.ui.theme.DeepBlue

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = DeepBlue,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}