package com.production.quizharrypotterapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.production.quizharrypotterapp.R
import com.production.quizharrypotterapp.navigation.AppScreens
import com.production.quizharrypotterapp.viewmodel.ViewModelGameQuiz

@Composable
fun QuizScreen(
    navController: NavController,
    count: Int,
    score: Int,
    timeRemaining: Int,
    question: String,
    answers: List<String>,
    viewModel: ViewModelGameQuiz,
    showDialog: Boolean,
    selectAnswerPlayer: Int,
    onAnswerSelected: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.fondo_pergamino),
            contentDescription = null
        )
        Column(modifier = Modifier.fillMaxSize()) {
            ScoreAndTime(count = count, score = score, timeRemaining = timeRemaining)
            ImageShieldHogwarts()
            QuestionAndAnswer(
                selectAnswerPlayer = selectAnswerPlayer,
                question = question,
                answers = answers,
                onAnswerSelected = onAnswerSelected
            )
            ButtonNextQuestion(viewModel)
            DialogWindow(
                score = score,
                viewModel = viewModel,
                showDialog = showDialog,
                navController = navController
            )
        }
    }
}

@Composable
fun DialogWindow(
    score: Int,
    viewModel: ViewModelGameQuiz,
    showDialog: Boolean,
    navController: NavController
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "Score", fontSize = 55.sp, fontWeight = FontWeight.ExtraBold) },
            text = {
                Text(
                    text = "Your score is:\n$score points",
                    fontSize = 33.sp,
                    softWrap = true
                )
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.cancelDialog()
                    navController.navigate(AppScreens.EnterScreen.route)
                }) {
                    Text(fontSize = 22.sp, text = "Return")
                }
            },
            dismissButton = {
                Button(onClick = {
                    viewModel.cancelDialog()
                    navController.navigate(AppScreens.RankingScreen.route)
                }) {
                    Text(fontSize = 22.sp, text = "Ranking")
                }
            })
    }
}

@Composable
fun QuestionAndAnswer(
    question: String,
    answers: List<String>,
    selectAnswerPlayer: Int,
    onAnswerSelected: (Int) -> Unit
) {
    var show by remember { mutableStateOf(false) }

    LaunchedEffect(answers) {
        show = true
    }

    val opacity: Float by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = tween(durationMillis = 3000), label = ""
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        answers.forEachIndexed { index, answer ->

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable {
                    onAnswerSelected(index)
                }
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectAnswerPlayer == index,
                    onClick = {
                        onAnswerSelected(index)
                    },
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .scale(if (selectAnswerPlayer == index) 1.2f else 1.0f)
                        .animateContentSize()
                )
                Text(
                    text = answer,
                    fontSize = 28.sp,
                    color = Color.Black,
                    modifier = Modifier.alpha(opacity)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ScoreAndTime(count: Int, score: Int, timeRemaining: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), horizontalArrangement = Arrangement.End
    ) {
        CountQuestion(count = count)
        TotallyScore(score)
        TimeQuestion(timeRemaining)
    }
}

@Composable
fun TotallyScore(score: Int) {

    Row {
        Text(text = "Score:", fontSize = 28.sp)
        Text(
            modifier = Modifier.padding(start = 4.dp, end = 30.dp),
            text = score.toString(),
            fontSize = 30.sp
        )
    }
}

@Composable
fun TimeQuestion(timeRemaining: Int) {

    Row {
        Text(text = "Time:", fontSize = 28.sp)
        Text(
            modifier = Modifier.padding(start = 4.dp, end = 10.dp),
            text = timeRemaining.toString(),
            fontSize = 30.sp
        )
    }
}

@Composable
fun CountQuestion(count: Int) {
    Row {
        Text(
            modifier = Modifier.padding(start = 4.dp, end = 30.dp),
            fontSize = 28.sp,
            text = "$count / 10"
        )
    }
}

@Composable
fun ButtonNextQuestion(viewModel: ViewModelGameQuiz) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .size(width = 130.dp, height = 50.dp),
            onClick = { viewModel.answerCorrect() }
        ) {
            Text(
                text = "Next",
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun ImageShieldHogwarts() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(top = 3.dp, bottom = 10.dp),
            painter = painterResource(id = R.drawable.logo_hogwarts),
            contentDescription = "Shield Hogwarts"
        )
    }
}