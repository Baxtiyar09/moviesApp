package com.example.atlmovaapp.screen.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.local.download.DownloadDao
import com.example.atlmovaapp.model.database.DowloadModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DownloadViewModel @Inject constructor(
    val dao: DownloadDao
) : ViewModel() {
    val downloadFilm = MutableLiveData<List<DowloadModel>>()

    fun addDownload(download: DowloadModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.addDownload(download)
            getAllDownload()
        }
    }

    fun getAllDownload() {
        viewModelScope.launch {
            val allDownloads = dao.getAllDownloads()
            downloadFilm.value = allDownloads
        }
    }


    fun deleteDownload(downloadId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteDownload(downloadId)
            getAllDownload()
        }
    }


    fun searchMyList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = dao.searchDownload(query)
            downloadFilm.postValue(movies)
        }
    }
}
