package com.example.tmdb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.tmdb.R
import com.example.tmdb.ui.theme.DeepBlue
import com.example.tmdb.ui.theme.GrayCircle


@Composable
fun MainImageDetails(painter: Painter
                     , title : String
                     , year : Int
                     , date: String
                     , genre : String
                     ,duration: String

){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(303.dp)
        .zIndex(5f)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Box(modifier = Modifier
            .height(303.dp)
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black))
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ){
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                UserScore(76f)
                Text(
                    fontSize = 20.sp,
                    color = Color.White,
                    text = "User Score",
                    modifier = Modifier,
                    textAlign = TextAlign.Justify
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "($year)",
                    fontSize = 30.sp,
                    color = Color.White,
                )
            }

            Text(
                text = date,
                fontSize = 15.sp,
                color = Color.White
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = genre,
                    fontSize = 15.sp,
                    color = Color.White
                )

                Text(
                    text = duration,
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Star()
        }

    }
}


@Composable
fun UserScore(score: Float){
    Box(modifier = Modifier
        .height(60.dp)
        .width(60.dp)
        ) {
        val angle: Float = score / 100 * 360
        Box(modifier = Modifier
            .align(Alignment.Center)
            .zIndex(3f)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                Text(
                    text = score.toInt().toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                )
                Text(
                    text = "%",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                )
            }
        }
        Canvas(modifier = Modifier
            .fillMaxSize()
        ) {
            drawCircle(
                color = Color.Black,
                radius = 80f,
                center = Offset(80f, 80f),
                alpha = 0.5f
            )
            drawArc(
                topLeft = Offset(0f,5f),
                color = Color.Green,
                startAngle = 270f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(width = 10f, cap = StrokeCap.Round),
                size = Size(
                    width = 160f,
                    height = 160f
                )
            )

        }
    }

}

@Composable
fun Star(){
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(GrayCircle.copy(alpha = 0.6f)),
    ){
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center)

        )
    }
}