package com.example.atlmovaapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_list")
data class MyListModel(
    @PrimaryKey val id: Int,
    val title: String,   //🔹
    val reyting: Double,
    val image: String,
    val selected: Boolean = false
)
