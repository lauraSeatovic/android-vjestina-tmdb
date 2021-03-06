package com.example.tmdb.ui.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.tmdb.R
import com.example.tmdb.ui.theme.GrayCircle
import java.time.LocalDate


@Composable
fun MainImageDetails(
    painter: Painter,
    title: String,
    year: Int,
    date: String,
    genre: String,
    duration: Int,
    score: Float


) {
    val year = date.substring(0, 4)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(303.dp)
            .zIndex(5f)
    ) {
        BackgroundImage(painter)


        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                UserScore(score)
                TextFormat(text = "User score", size = 20.sp, weight = FontWeight.Normal)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(0.9f)
            ) {

                TextFormat(text = "$title ($year)", size = 25.sp, weight = FontWeight.Bold)
                //TextFormat(text = "($year)", size = 25.sp, weight = FontWeight.Bold)
            }

            TextFormat(text = date, size = 15.sp, weight = FontWeight.Normal)

            Row(
                modifier = Modifier
                    .padding(bottom = 5.dp)
            ) {
                Text(genre.substring(1, genre.length - 1), fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.White, modifier = Modifier.fillMaxWidth(0.7f))

            }
            Text(convertTime(duration), fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(bottom = 5.dp))


            Star()
        }

    }
}

@Composable
private fun TextFormat(text: String, size: TextUnit, weight: FontWeight){
    Text(
    text = text,
    fontSize = size,
    color = Color.White,
    fontWeight = weight
    )
}

@Composable
private fun BackgroundImage(painter: Painter){
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
    )

    Box(
        modifier = Modifier
            .height(303.dp)
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black))
            )
    )

}

@Composable
private fun UserScore(score: Float) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(60.dp)
    ) {
        val angle: Float = score / 10 * 360
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(3f)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                Text(
                    text = (score*10).toInt().toString(),
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
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawCircle(
                color = Color.Black,
                radius = 80f,
                center = Offset(80f, 80f),
                alpha = 0.5f
            )
            drawArc(
                topLeft = Offset(0f, 5f),
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
private fun Star() {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(GrayCircle.copy(alpha = 0.6f)),
    ) {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center)
        )
    }
}

private fun convertTime(time: Int): String{
    val h = time / 60
    val m = time % 60
    return if (h==0)
        (m.toString() + "m")
    else
        (h.toString() + "h" + m.toString() + "m")
}