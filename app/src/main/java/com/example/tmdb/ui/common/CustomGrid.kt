package com.example.tmdb.ui.common

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomGrid(size: Int, columns: Int, data: List<Any>) {

    var rows = size / columns
    if (size % columns != 0) rows += 1
    Log.i("size", size.toString())
    var counter = 0
    var counterRows = 0
    var counterColumns: Int

    while (counterRows < rows) {
        counterColumns = 0
        Row() {
            while ((counterColumns < columns) and (counter < size)) {
                data[counter]
                counterColumns += 1
                counter += 1
            }
        }
        counterRows += 1
    }
}

