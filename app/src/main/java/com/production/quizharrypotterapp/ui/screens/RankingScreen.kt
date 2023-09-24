package com.production.quizharrypotterapp.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.production.quizharrypotterapp.R
import com.production.quizharrypotterapp.db.Player
import com.production.quizharrypotterapp.navigation.AppScreens
import com.production.quizharrypotterapp.viewmodel.ViewModelGameQuiz

@Composable
fun RankingScreen(navController: NavController, viewModel: ViewModelGameQuiz) {

    val playerList by viewModel.playersList.observeAsState(emptyList())
    val visibleItems = playerList.take(15)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.fondo_sombrero),
            contentDescription = "Hat"
        )
        LazyColumn {
            items(visibleItems.size) { index ->
                val offsetY by animateDpAsState(
                    targetValue = if (index in visibleItems.indices) 0.dp else 100.dp,
                    animationSpec = tween(500)
                )

                CardItem(
                    player = visibleItems[index],
                    modifier = Modifier
                        .offset(y = offsetY)
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .size(width = 130.dp, height = 50.dp),
                        onClick = {
                            viewModel.cancelDialog()
                            navController.navigate(AppScreens.EnterScreen.route)
                        }
                    ) {
                        Text(text = "Return", fontSize = 30.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(player: Player, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = player.player,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 20.dp)
            )
            Text(
                text = "Score: ${player.score}",
                fontSize = 30.sp,
            )
        }
    }
}