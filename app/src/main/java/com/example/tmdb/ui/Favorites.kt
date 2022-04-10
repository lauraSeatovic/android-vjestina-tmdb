package com.example.tmdb.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tmdb.R
import com.example.tmdb.WritersData

@Composable
fun Favorites(navController: NavController){
    val configuration = LocalConfiguration.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)

    ){
        Column(modifier = Modifier
                    .fillMaxWidth()

        ) {
        Logo()
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            Title("Favorites")
        }
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .align(Alignment.CenterHorizontally)

        ) {
            item {
                val movieList: List<Painter> = listOf(
                    painterResource(id = R.drawable.gattaca),
                    painterResource(id = R.drawable.ironman),
                    painterResource(id = R.drawable.ironman2),
                    painterResource(id = R.drawable.godzilla),
                    painterResource(id = R.drawable.puppylove),
                    painterResource(id = R.drawable.gattaca),
                    painterResource(id = R.drawable.puppylove),
                    painterResource(id = R.drawable.ironman),
                    painterResource(id = R.drawable.junglebeat),
                    painterResource(id = R.drawable.gattaca),
                    painterResource(id = R.drawable.ironman2),
                    painterResource(id = R.drawable.junglebeat)
                )
                Box(modifier = Modifier
                        .padding(vertical = 20.dp)
                ) {
                    FavoriteGrid(movieList.size, movieList)
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
        }
    }
}

@Composable
fun FavoriteGrid(quantity: Int, names: List<Painter>){
    var rows: Int = quantity / 3
    if(quantity % 3 != 0){
        rows+=1
    }

    var counter: Int = 0
    var counterRows: Int = 0
    var counterColumns: Int
    Column(modifier = Modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        while(counterRows < rows){
            counterColumns = 0
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                while ((counterColumns < 3) and (counter < quantity)) {

                        FavoriteCard(painter = names[counter])

                        counterColumns += 1
                        counter += 1
                }
            }
            counterRows+=1

        }
    }
}

@Composable
fun FavoriteCard(painter: Painter){
    Card(modifier = Modifier
        .height(160.dp)
        .width(100.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ){
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(5.dp)){
                Heart()
            }
        }

    }
}