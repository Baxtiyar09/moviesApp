package com.example.atlmovaapp.screen.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: AppRepository
) : ViewModel()  {
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val popular = MutableLiveData<List<Result>>()
    val topRated = MutableLiveData<List<Result>>()
    val upcoming = MutableLiveData<List<Result>>()
    val nowPlaying = MutableLiveData<List<Result>>()
    private val _featuredMovie = MutableLiveData<Result?>()
    val featuredMovie: MutableLiveData<Result?> get() = _featuredMovie

    fun getPopular(){
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getPopularFlow().collect{ response ->
                    if (response.isSuccessful){
                       response.body()?.let {
                           loading.value = false
                           popular.value = it.results ?: emptyList()
                       }
                    }
                }
            }catch (e:Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }

    fun getTopRated(){
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getTopRatedFlow().collect{ response ->
                    if (response.isSuccessful){
                        response.body()?.let {
                            loading.value = false
                            topRated.value = it.results ?: emptyList()
                        }
                    }
                }
            }catch (e:Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }


    fun getUpcoming(){
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getUpcomingFlow().collect{ response ->
                    if (response.isSuccessful){
                        response.body()?.let {
                            loading.value = false
                            upcoming.value = it.results ?: emptyList()
                        }
                    }
                }
            }catch (e:Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }


    fun getNowPlaying(){
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getNowPlayingFlow().collect{ response ->
                    if (response.isSuccessful){
                        response.body()?.let {
                            loading.value = false
                            nowPlaying.value = it.results ?: emptyList()
                        }
                    }
                }
            }catch (e:Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }



    fun loadFeaturedMovie() {
        viewModelScope.launch {
            repository.getPopularFlow().collect { response ->
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        val first = it.firstOrNull()
                        _featuredMovie.value = first
                        popular.value = it
                    }
                }
            }
        }
    }


}