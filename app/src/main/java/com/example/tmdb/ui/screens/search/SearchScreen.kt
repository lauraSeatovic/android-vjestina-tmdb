package com.example.tmdb.ui.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.tmdb.repository.Movie
import com.example.tmdb.ui.common.Logo
import com.example.tmdb.ui.screens.details.SearchWidget
import com.example.tmdb.viewModels.HomeViewModel
import com.example.tmdb.viewModels.SearchViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel: SearchViewModel by viewModel()
    val result = searchViewModel.viewState.collectAsState()
    var text by remember { mutableStateOf("") }
    SearchLayout(
        input = text,
        onSearchTextChanged = { input ->
            text = input
            searchViewModel.getInput(input)

        },
        onSearchCardClick = { id -> navController.navigate("details_screen/$id") },
        result = result.value
    )
}

@Composable
fun SearchLayout(
    input: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchCardClick: (Int) -> Unit,
    result: List<Movie>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Logo()
        SearchWidget(onSearchTextChanged, input)
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(result.size) { item ->
                SearchResultCard(result[item], onSearchCardClick)
            }
        }
    }

}
