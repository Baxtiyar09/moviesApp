package com.example.atlmovaapp.screen.myList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.local.mylist.MyListDao
import com.example.atlmovaapp.model.database.MyListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val dao: MyListDao
) : ViewModel() {

    val myList = MutableLiveData<List<MyListModel>>()

    fun addMovie(movie: MyListModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.addMovie(movie)
            getAllMovie()
        }
    }

    fun getAllMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = dao.getAllMovie()
            myList.postValue(movies)
        }
    }

    fun deleteMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteMovie(movieId)
            getAllMovie()
        }
    }

    // 🔎 Search
    fun searchMyList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = dao.searchMyList(query)
            myList.postValue(movies)
        }
    }
}
