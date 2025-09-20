package com.example.atlmovaapp.local.download

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.atlmovaapp.model.database.DowloadModel
import com.example.atlmovaapp.model.database.MyListModel

@Dao
interface DownloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDownload(download: DowloadModel)

    @Query("SELECT * FROM download_table")
    suspend fun getAllDownloads(): List<DowloadModel>

    @Query("DELETE FROM download_table WHERE id = :downloadId")
    suspend fun deleteDownload(downloadId: Int)

    @Query("DELETE FROM download_table")
    suspend fun deleteDownload()

    @Query("SELECT * FROM download_table WHERE name LIKE '%' || :query || '%'")
    suspend fun searchDownload(query: String): List<DowloadModel>
}