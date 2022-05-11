package com.example.tmdb.ui.screens.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdb.data.ContainerData
import com.example.tmdb.repository.Movie
import com.example.tmdb.ui.common.MovieCard
import com.example.tmdb.ui.theme.*


@Composable
fun MoviesContainer(favorites: List<Movie>, container: ContainerData, onMovieCardClick: (Int) -> Unit, onFavoriteClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp)

    ) {
        Text(
            text = container.title,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = DeepBlue
        )


        val listState = rememberLazyListState()
        var selectedIndex by remember { mutableStateOf(1) }

        LazyRow(
            state = listState,
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = container.buttons) { item ->
                Text(
                    text = item.message,
                    color = if (item.id == selectedIndex) DeepBlue else Gray,
                    fontWeight = if (item.id == selectedIndex) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidth = 3 * density
                            val y = size.height - strokeWidth / 2

                            drawLine(
                                color = if (item.id == selectedIndex) DeepBlue else Color.Transparent,
                                Offset(0f, y),
                                Offset(size.width, y),
                                strokeWidth
                            )
                        }
                        .padding(bottom = 5.dp)
                        .padding(horizontal = 2.dp)
                        /*.background(
                            if (item.id == selectedIndex)
                                Color.Red else Color.Transparent
                        )*/
                        .selectable(
                            selected = item.id == selectedIndex,
                            onClick = {
                                if (selectedIndex != item.id)
                                    selectedIndex = item.id else selectedIndex = -1
                            })
                )
            }
        }
        LazyRow(state = listState, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(items = container.movies) { item ->
                MovieCard(favorites, item, onMovieCardClick, onFavoriteClick, 179.dp, 122.dp)
            }
        }
    }
}

