package com.example.tmdb.ui.screens.details


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.ui.theme.DeepBlue
import com.example.tmdb.ui.theme.LightGray


@Composable
fun SearchWidget(
    onSearchTextChanged: (String) -> Unit,
    input: String
) {
    val focusRequester = FocusRequester()
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
    TextField(
        value = input,
        onValueChange = { newInput -> onSearchTextChanged(newInput) },
        //onValueChange = {},
        singleLine = true,
        placeholder = { Text(text = "Search", color = DeepBlue) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "searchIcon",
                tint = DeepBlue
            )
        },
        modifier = Modifier
            .padding(25.dp)
            .height(55.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(10),
        colors = TextFieldDefaults.textFieldColors(
            textColor = DeepBlue,
            backgroundColor = LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}

