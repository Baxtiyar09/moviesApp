package com.example.atlmovaapp.screen.seeAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.api.ApiServices
import com.example.atlmovaapp.model.MovaListModel
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.util.Constants.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val api: ApiServices
) : ViewModel() {

    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> = _movies

    fun fetchMovies(category: String) {
        viewModelScope.launch {
            try {
                val response = when(category) {
                    "now_playing" -> api.getNowPlayingMovies()
                    "popular" -> api.getPopularMovies()
                    "top_rated" -> api.getTopRatedMovies()
                    "upcoming" -> api.getUpcomingMovies()
                    else -> null
                }

                response?.takeIf { it.isSuccessful }?.body()?.results?.let { list ->
                    _movies.value = list
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
