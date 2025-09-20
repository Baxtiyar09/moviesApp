package com.example.atlmovaapp.model.card

import android.support.annotation.DrawableRes

data class PaymentModel(
    @DrawableRes val image: Int,
    val title: String,
    var selected: Boolean,
    var cardNumber: String = "",

    )