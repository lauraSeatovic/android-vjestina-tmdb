package com.example.tmdb.ui.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdb.data.CrewData
import com.example.tmdb.data.WritersData


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WritersGrid(data: List<CrewData>) {
    var quantity = data.size
    if (data.size > 6) {
        quantity = 6
    }
    var rows: Int = quantity / 3
    if (quantity % 3 != 0) {
        rows += 1
    }

    var counter = 0
    var counterRows = 0
    var counterColumns: Int
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        while (counterRows < rows) {
            counterColumns = 0
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                while ((counterColumns < 3) and (counter < quantity)) {
                    CrewCard(item = data[counter])
                    counterColumns += 1
                    counter += 1
                }
            }
            counterRows += 1
        }
    }
}

@Composable
fun CrewCard(item: CrewData) {
    Card(modifier = Modifier.width(95.dp)) {
        Column() {
            Text(item.name, fontWeight = FontWeight.Bold)
            Text(item.job)
        }
    }
}