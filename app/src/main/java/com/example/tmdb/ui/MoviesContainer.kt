package com.example.tmdb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tmdb.ContainerData
import com.example.tmdb.R
import com.example.tmdb.Screen
import com.example.tmdb.ui.theme.*


@Composable
fun MoviesContainer(container: ContainerData, navController: NavController){
    Column(modifier = Modifier
        .padding(10.dp)

    ) {
        Text(
            text = container.title,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = DeepBlue
        )


        val listState = rememberLazyListState()
        var selectedIndex by remember{mutableStateOf(1)}

        LazyRow(state = listState, modifier = Modifier.padding(vertical = 10.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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

        LazyRow(state = listState, horizontalArrangement = Arrangement.spacedBy(16.dp)){
            items(items = container.images[selectedIndex - 1]){
                item -> MovieCard(item, navController = navController)
            }
        }


    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(painter: Painter, navController: NavController){
    Card(modifier = Modifier
        .height(179.dp)
        .width(122.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = {navController.navigate(Screen.Details.route)}
    ){
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(5.dp)){
                Heart()
            }
        }

    }
}

@Preview
@Composable
fun Heart(){
    Box(
        modifier = Modifier
            .size(35.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(DeepBlue.copy(alpha = 0.6f)),
    ) {
        var isFavorite by remember { mutableStateOf(false) }

        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = !isFavorite
            }
        ) {
            Icon(
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center)

            )
        }
    }
}