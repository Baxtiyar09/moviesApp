package com.example.atlmovaapp.screen.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NotificationViewModel @Inject constructor(
    val repository: AppRepository
) : ViewModel() {
    val popular = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    fun getPopular() {
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getPopularFlow().collect { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            loading.value = false
                            popular.value = it.results
                        }
                    }
                }
            } catch (e: Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }



}