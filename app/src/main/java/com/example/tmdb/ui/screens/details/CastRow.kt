package com.example.tmdb.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.data.ButtonData
import com.example.tmdb.data.CrewData
import com.example.tmdb.ui.common.MovieCard
import com.example.tmdb.ui.theme.DeepBlue
import com.example.tmdb.ui.theme.Gray


@Composable
fun CastRow(castList: List<List<CrewData>>) {

    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableStateOf(0) }
    val text =
        listOf<ButtonData>(ButtonData(0, "Top Billed Cast"), ButtonData(1, "Full Cast & Crew"))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        state = listState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(items = text) { item ->
            Text(
                text = item.message,
                color = DeepBlue,
                fontWeight = if (item.id == selectedIndex) FontWeight.Bold else FontWeight.W500,
                fontSize = if (item.id == selectedIndex) 25.sp else 20.sp,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .padding(horizontal = 2.dp)
                    .selectable(
                        selected = item.id == selectedIndex,
                        onClick = {
                            if (selectedIndex != item.id)
                                selectedIndex = item.id
                        })
            )
        }
    }
    LazyRow(
        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(items = castList[selectedIndex]) { item ->
            CastCard(item)
        }
    }


}


@Composable
fun CastCard(actor: CrewData) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        modifier = Modifier
            .padding(end = 20.dp)
            .padding(bottom = 20.dp)
            .height(230.dp)
            .width(130.dp)
    ) {
        Column() {

            Image(
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${actor.image}"),
                contentDescription = actor.name,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .fillMaxSize()
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = actor.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = actor.job,
                    fontSize = 12.sp
                )
            }
        }
    }
}