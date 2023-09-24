package com.production.quizharrypotterapp

import android.app.Application
import androidx.room.Room
import com.production.quizharrypotterapp.db.QuestionLocalDataSource
import com.production.quizharrypotterapp.db.Repository
import com.production.quizharrypotterapp.room.DatabaseRoom
import com.production.quizharrypotterapp.room.RankingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideQuestionLocalDataSource(): QuestionLocalDataSource {
        return QuestionLocalDataSource()
    }

    @Provides
   fun provideQuestionRepository(questionLocalDataSource: QuestionLocalDataSource, application: Application): Repository {
        return Repository(questionLocalDataSource, providesRankingDao(providesRankingDb(application)))
    }

    @Provides
    fun providesRankingDb(
        application: Application
    ) = Room.databaseBuilder(application, DatabaseRoom::class.java, "RANKING").build()

    @Provides
    fun providesRankingDao( roomDb: DatabaseRoom): RankingDao = roomDb.playerDao()
}
