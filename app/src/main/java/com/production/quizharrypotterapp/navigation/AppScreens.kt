package com.production.quizharrypotterapp.navigation

sealed class AppScreens( val route: String ) {
    object EnterScreen: AppScreens("enter_screen")
    object InfoScreen: AppScreens("info_screen")
    object QuizScreen: AppScreens("quiz_screen")
    object RankingScreen: AppScreens("ranking_screen")
}