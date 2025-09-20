package com.example.atlmovaapp.aut.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : ViewModel() {
    val isSucces = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun signUp(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                if (!response.user?.uid.isNullOrEmpty()) {
                    isSucces.value = true
                } else {
                    error.value = "Error"
                    loading.value = false
                }
            } catch (e: Exception) {
                loading.value = false
                error.value = e.localizedMessage
            }
        }
    }
}