package com.example.tmdb.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdb.WritersData


@Composable
fun WritersGrid(quantity: Int, names: List<WritersData>){
    var rows: Int = quantity / 3
    if(quantity % 3 != 0){
        rows+=1
    }

    var counter: Int = 0
    var counterRows: Int = 0
    var counterColumns: Int
    Column(modifier = Modifier
        .padding(top = 20.dp, start = 20.dp, end = 20.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        while(counterRows < rows){
            counterColumns = 0
            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                while ((counterColumns < 3) and (counter < quantity)) {
                    Column() {
                        Text(
                            text = names[counter].name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(text = names[counter].job)
                        counterColumns += 1
                        counter += 1
                    }
                }
            }
            counterRows+=1

        }
    }
}