package com.example.tmdb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdb.CastData


@Composable
fun CastRow(castList: List<CastData>){
    LazyRow(
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.padding(top = 20.dp, start = 20.dp)

    ){
        items(items = castList){
                item -> CastCard(item)
        }
    }
}


@Composable
fun CastCard(actor: CastData) {
    Box(modifier = Modifier
                    .padding(end = 20.dp)
                    .padding(bottom = 20.dp)

    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp,
            modifier = Modifier
                .height(230.dp)
                .width(130.dp)
        ) {
            Column() {
                Box(
                    modifier = Modifier
                        .height(130.dp)
                        .width(130.dp)
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        painter = actor.image,
                        contentDescription = actor.name,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column() {
                        Text(
                            text = actor.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = actor.role,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}