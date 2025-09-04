package com.example.atlmovaapp.screen.serach

import android.R.attr.id
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SerachViewModel @Inject constructor(
    val repository: AppRepository
): ViewModel() {
    val allMovies = MutableLiveData<List<Result>>()
    val serachMovies = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()

    fun search(title: String) {
        viewModelScope.launch {
            try {
                repository.search(title).collect { response ->
                    if (response.isSuccessful){
                        response.body()?.results?.let {
                            serachMovies.value = it
                        }
                    }
                }

            }catch (e: Exception){
                error.value = e.localizedMessage
            }
        }
    }


    fun getMovie() {
        viewModelScope.launch {
//            loading.value = true
            try {
                repository.getPopularFlow().collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.results?.let {
                            allMovies.value = it
                        }
                    }
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }



}