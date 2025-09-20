package com.example.atlmovaapp.screen.seeAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.api.ApiServices
import com.example.atlmovaapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val api: ApiServices
) : ViewModel() {

    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> = _movies

    private var allMovies: List<Result> = emptyList() // 🔹 bütün filmləri saxlayırıq


    fun fetchMovies(category: String) {
        viewModelScope.launch {
            try {
                val response = when (category) {
                    "now_playing" -> api.getNowPlayingMovies()
                    "popular" -> api.getPopularMovies()
                    "top_rated" -> api.getTopRatedMovies()
                    "upcoming" -> api.getUpcomingMovies()
                    else -> null
                }

                response?.takeIf { it.isSuccessful }?.body()?.results?.let { list ->
                    allMovies = list // 🔹 tam siyahını saxla
                    _movies.value = list
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    // 🔎 Axtarış funksiyası (yalnız mövcud siyahıdan filter edir)
    fun filterMovies(query: String) {
        if (query.isEmpty()) {
            _movies.value = allMovies
        } else {
            _movies.value = allMovies.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }
    }
}
