package com.example.atlmovaapp.local.mylist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.atlmovaapp.model.database.MyListModel

@Dao
interface MyListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: MyListModel)

    @Query("SELECT * FROM my_list")
    suspend fun getAllMovie(): List<MyListModel>

    @Query("DELETE FROM my_list WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)

    // 🔎 Search — title-a görə axtarış
    @Query("SELECT * FROM my_list WHERE title LIKE '%' || :query || '%'")
    suspend fun searchMyList(query: String): List<MyListModel>

    @Query("SELECT EXISTS(SELECT 1 FROM my_list WHERE id = :movieId)")
    suspend fun isMovieInList(movieId: Int): Boolean
}
