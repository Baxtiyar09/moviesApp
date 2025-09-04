package com.example.atlmovaapp.model.detail.trailers


import com.google.gson.annotations.SerializedName

data class TrailersResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<ResultTrailers>
)
