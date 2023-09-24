package com.production.quizharrypotterapp.db

import com.production.quizharrypotterapp.room.RankingDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val questionLocalDataSource: QuestionLocalDataSource,
    private val dao: RankingDao
) {

     suspend fun addPlayer(player: Player) = dao.insertPlayer(player)
      suspend fun getRanking() = dao.getRanking()

    val question: List<Question> = questionLocalDataSource.listQuestion
}