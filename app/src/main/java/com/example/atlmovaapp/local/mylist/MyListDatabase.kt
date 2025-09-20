package com.example.atlmovaapp.local.mylist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.atlmovaapp.model.database.MyListModel

@Database(entities = [MyListModel::class], version = 3, exportSchema = false)
abstract class MyListDatabase : RoomDatabase() {
    abstract fun myListDao(): MyListDao
}