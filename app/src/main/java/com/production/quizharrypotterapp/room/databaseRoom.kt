package com.production.quizharrypotterapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.production.quizharrypotterapp.db.Player

 @Database(entities = [Player::class], version = 1, exportSchema = false)
 abstract class DatabaseRoom: RoomDatabase() {
    abstract fun playerDao(): RankingDao
 }
