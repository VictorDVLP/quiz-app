package com.production.quizharrypotterapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.production.quizharrypotterapp.R
import com.production.quizharrypotterapp.navigation.AppScreens
import com.production.quizharrypotterapp.viewmodel.ViewModelGameQuiz

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntraceScreen(navController: NavController, viewModel: ViewModelGameQuiz) {
    Scaffold {
        EnterApp(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterApp(navController: NavController, viewModel: ViewModelGameQuiz) {

    var playerName by rememberSaveable { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.harrypotterbackground),
            contentScale = ContentScale.Crop,
            contentDescription = "Background main"
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .size(width = 250.dp, height = 90.dp)
                    .align(Alignment.CenterHorizontally),
                value = playerName,
                onValueChange = { if (it.length <= 15) playerName = it },
                label = { Text("Player Name") },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.dumbledor)),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                ),
                singleLine = true,
                supportingText = {
                    Text(
                        text = "${playerName.length} / 15",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                modifier = Modifier
                    .size(width = 180.dp, height = 60.dp),
                enabled = playerName.isNotEmpty(),
                onClick = {
                    viewModel.setNamePlayer(playerName)
                    viewModel.starTimer()
                    navController.navigate(AppScreens.QuizScreen.route)
                }
            ) {
                Text(
                    fontSize = 33.sp, text = "Play Quiz",
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier
                    .size(width = 130.dp, height = 50.dp),
                onClick = { navController.navigate(AppScreens.InfoScreen.route) }
            ) {
                Text(
                    fontSize = 21.sp, text = "Info Quiz",
                )
            }
        }
    }
}