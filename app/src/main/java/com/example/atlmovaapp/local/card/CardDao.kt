package com.example.atlmovaapp.local.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.atlmovaapp.model.database.CardModel

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(card: CardModel)

    @Query("SELECT * FROM card_table")
    suspend fun getAllCards(): List<CardModel>

    @Query("DELETE FROM card_table WHERE id = :cardId")
    suspend fun deleteCard(cardId: Int)

    @Query("DELETE FROM card_table")
    suspend fun deleteCard()
}