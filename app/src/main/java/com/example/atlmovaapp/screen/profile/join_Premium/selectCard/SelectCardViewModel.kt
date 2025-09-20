package com.example.atlmovaapp.screen.profile.join_Premium.selectCard

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
class SelectCardViewModel @Inject constructor(
    private val dao: CardDao
) : ViewModel() {

    val cards = MutableLiveData<List<CardModel>>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getAllCards() {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = dao.getAllCards()
                cards.postValue(list)
                loading.postValue(false)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
                loading.postValue(false)
            }
        }
    }

    fun deleteCard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.deleteCard()
                getAllCards()
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }
}
