package com.production.quizharrypotterapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

 @Entity(tableName = "ranking")
 data class Player(
     @PrimaryKey(autoGenerate = true) val id: Int = 0,
     @ColumnInfo val player: String,
     @ColumnInfo val score: Int
)

