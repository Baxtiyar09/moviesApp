package com.example.atlmovaapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val number: String,
    val date: String,
    val cvv: String,
    var selected: Boolean,
    val isDefault: Boolean = false
)
