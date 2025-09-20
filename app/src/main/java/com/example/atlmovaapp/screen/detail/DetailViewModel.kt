package com.example.atlmovaapp.screen.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.model.detail.MovieDetailResponse
import com.example.atlmovaapp.model.detail.flimCredits.CreditsResponse
import com.example.atlmovaapp.model.detail.reviews.ResultReviews
import com.example.atlmovaapp.model.detail.trailers.ResultTrailers
import com.example.atlmovaapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val repository: AppRepository
) : ViewModel() {
    val credits = MutableLiveData<CreditsResponse>()
    val detail = MutableLiveData<MovieDetailResponse>()
    val trailers = MutableLiveData<List<ResultTrailers>>()
    val moreLikeThis = MutableLiveData<List<Result>>()
    val reviews = MutableLiveData<List<ResultReviews>>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getMovieDetail(id: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getMovieDetailFlow(id).collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            loading.value = false
                            detail.value = data
                        }
                    } else {
                        error.value = response.message()
                    }
                }

            } catch (e: Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }


    fun getMovieCredits(id: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getMovieCreditsFlow(id).collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            loading.value = false
                            credits.value = it
                        }
                    }
                }
            } catch (e: Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }


    fun getMovieTrailers(id: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getMovieTrailersFlow(id).collect { response ->
                    if (response.results.isNotEmpty()) {
                        trailers.value = response.results
                    }
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }

    fun getMovieMoreLikeThis() {
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getUpcomingFlow().collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            loading.value = false
                            moreLikeThis.value = it.results
                        }
                    }
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }

    fun getMovieReviews(id: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getMovieReviewsFlow(id).collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.results?.let {
                            withContext(Dispatchers.Main) {
                                loading.value = false
                                reviews.value = it
                                Log.d("reviews", it.toString())
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }

        }
    }


    suspend fun isMovieAdded(id: Int): Boolean {
        return repository.isMovieAdded(id)
    }
}