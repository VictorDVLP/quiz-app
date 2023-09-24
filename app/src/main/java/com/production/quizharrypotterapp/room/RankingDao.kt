package com.production.quizharrypotterapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.production.quizharrypotterapp.db.Player

@Dao
interface RankingDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertPlayer( player: Player )

    @Query("SELECT * FROM ranking ORDER BY score DESC")
    suspend fun getRanking(): List<Player>
 }