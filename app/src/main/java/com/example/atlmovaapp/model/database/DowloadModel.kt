package com.example.atlmovaapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_table")
data class DowloadModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val releaseDate: String,
    val gb: String,
    val image: String
)
