package com.production.quizharrypotterapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.production.quizharrypotterapp.ui.screens.EntraceScreen
import com.production.quizharrypotterapp.ui.screens.Instructions
import com.production.quizharrypotterapp.ui.screens.QuizScreen
import com.production.quizharrypotterapp.ui.screens.RankingScreen
import com.production.quizharrypotterapp.viewmodel.ViewModelGameQuiz

@Composable
fun AppNavigation() {
    val viewModel: ViewModelGameQuiz = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.EnterScreen.route) {
        composable(route = AppScreens.EnterScreen.route) {
            EntraceScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = AppScreens.InfoScreen.route) {
            Instructions(navController = navController)
        }
        composable(route = AppScreens.QuizScreen.route) {

            val question: String by viewModel.question.observeAsState("")
            val listAnswers: List<String> by viewModel.answers.observeAsState(emptyList())
            val selectAnswersPlayer: Int by viewModel.selectAnswerPlayer.observeAsState(0)
            val showDialog: Boolean by viewModel.showDialog.observeAsState(false)
            val score: Int by viewModel.score.observeAsState(0)
            val count: Int by viewModel.countQuestion.observeAsState(0)
            val time: Int by viewModel.timeLeft.observeAsState(0)
            QuizScreen(
                navController = navController,
                count = count,
                selectAnswerPlayer = selectAnswersPlayer,
                score = score,
                timeRemaining = time,
                viewModel = viewModel,
                showDialog = showDialog,
                question = question,
                answers = listAnswers,
            ) { selectedAnswer -> viewModel.updateSelectAnswerPlayer(selectedAnswer) }
        }
        composable(route = AppScreens.RankingScreen.route) {
            RankingScreen(navController = navController ,viewModel = viewModel)
        }
    }
}