package com.example.atlmovaapp.local.card

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.atlmovaapp.model.database.CardModel

@Database(entities = [CardModel::class], version = 5, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}