package com.example.atlmovaapp.local.download

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.atlmovaapp.model.database.DowloadModel

@Database(entities = [DowloadModel::class], version = 1, exportSchema = false)
abstract class DownloadDatabase : RoomDatabase() {
    abstract fun downloadDao(): DownloadDao

}