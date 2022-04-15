package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.navigation.RootNavGraph
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.rep.Movie
import com.example.tmdb.ui.Details
import com.example.tmdb.ui.Favorites
import com.example.tmdb.ui.HomeScreen
import com.example.tmdb.ui.theme.TmdbTheme
import com.example.tmdb.viewModels.DetailsViewModel
import com.example.tmdb.viewModels.FavoriteViewModel
import com.example.tmdb.viewModels.FavoriteViewState
import com.example.tmdb.viewModels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Observer

class MainActivity : ComponentActivity() {
    private val homeViewModel:HomeViewModel by viewModel()
    private val favoriteViewModel:FavoriteViewModel by viewModel()
    private val detailsViewModel:DetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {
                val rootNavController = rememberNavController()
                RootNavGraph(rootNavHostController = rootNavController)
            }
        }
    }
}

