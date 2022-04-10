package com.example.tmdb.ui

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tmdb.CastData
import com.example.tmdb.R
import com.example.tmdb.WritersData
import com.example.tmdb.ui.theme.DeepBlue


@Composable
@Preview
fun Details(navController: NavController) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Box() {
                    Logo()
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
                MainImageDetails(
                    painter = painterResource(id = R.drawable.ironman),
                    "Iron man",
                    2008,
                    "05/02/2008",
                    "Action, Science Fiction, Adventure",
                    "2h 6m"
                )
                Box(modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Title("Overview")
                }
                MovieDescription(description = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.")

                val writer1: WritersData = WritersData("Don Heck", "Characters")
                val writer2: WritersData = WritersData("Jack Kirby", "Characters")
                val writer3: WritersData = WritersData("Jon Favreau", "Director")
                val writer4: WritersData = WritersData("Don Heck", "Screenplay")
                val writer5: WritersData = WritersData("Don Heck", "Screenplay")

                WritersGrid(
                    quantity = 5,
                    names = listOf(writer1, writer2, writer3, writer4, writer5)
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Title(title = "Top Billed Cast")
                    Title2(title = "Full Cast & Crew")
                }

                val actor1: CastData = CastData(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron Man",
                    painterResource(id = R.drawable.robert)
                )
                val actor2: CastData = CastData(
                    "Terrence Howard",
                    "James Rhodes",
                    painterResource(id = R.drawable.terrence)
                )
                val actor3: CastData = CastData(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron Man",
                    painterResource(id = R.drawable.robert)
                )

                CastRow(castList = listOf(actor1, actor2, actor3))


            }
        }


    }
}


@Composable
fun Title(title: String){
    Text(
        text = title,
        color = DeepBlue,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
    )
}

@Composable
fun Title2(title: String){
    Text(
        text = title,
        color = DeepBlue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
    )
}

@Composable
fun MovieDescription(description: String){
    Text(
        text = description,
        color = Color.Black,
        fontSize = 17.sp,
        modifier = Modifier
            .padding(start = 20.dp)
            .padding(top = 5.dp)
    )
}

