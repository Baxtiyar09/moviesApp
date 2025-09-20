package com.example.atlmovaapp.screen.profile.join_Premium.addCard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovaapp.local.card.CardDao
import com.example.atlmovaapp.model.database.CardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewCardViewModel @Inject constructor(
    val dao: CardDao
) : ViewModel() {
    val error = MutableLiveData<String>()
    val card = MutableLiveData<CardModel>()
    val loading = MutableLiveData<Boolean>()

    fun addCard(card: CardModel) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.addCard(card)
                this@AddNewCardViewModel.card.postValue(card)
                loading.postValue(false)
            } catch (e: Exception) {
                error.postValue(e.message)
                loading.postValue(false)
            }
        }
    }
}