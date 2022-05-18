package com.example.tmdb.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.R
import com.example.tmdb.repository.Movie

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResultCard(movie: Movie, onSearchCardClick: (Int) -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(vertical = 10.dp),
        onClick = { onSearchCardClick(movie.id) }
    ) {
        Row(modifier = Modifier) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.image}"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
                    .padding(end = 5.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movie.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = movie.overview, fontSize = 15.sp)

            }
        }

    }
}