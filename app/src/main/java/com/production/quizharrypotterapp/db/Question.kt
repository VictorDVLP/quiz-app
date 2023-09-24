package com.production.quizharrypotterapp.db

import androidx.compose.runtime.Immutable

@Immutable
data class Question(
    val question: String,
    val optionsAnswer: List<String>,
    val correctAnswer: Int
    )
