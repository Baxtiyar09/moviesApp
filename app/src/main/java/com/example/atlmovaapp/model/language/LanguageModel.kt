package com.example.atlmovaapp.model.language

import com.google.gson.annotations.SerializedName

data class LanguageModel(
    @SerializedName("language")
    val language: String,
    var selected: Boolean = false
    )
