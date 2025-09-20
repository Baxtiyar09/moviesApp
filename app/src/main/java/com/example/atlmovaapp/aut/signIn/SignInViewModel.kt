package com.example.atlmovaapp.aut.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class SignInViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val isSucces = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                if (!response.user?.uid.isNullOrEmpty()) {
                    isSucces.value = true
                } else {
                    loading.value = false
                    isError.value = "User not found"
                }
            } catch (e: Exception) {
                loading.value = false
                isError.value = e.localizedMessage
            }
        }
    }
}