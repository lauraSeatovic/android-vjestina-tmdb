package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.navigation.RootNavGraph
import com.example.tmdb.ui.theme.TmdbTheme

class MainActivity : ComponentActivity() {
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

