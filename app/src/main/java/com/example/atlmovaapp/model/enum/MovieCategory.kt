package com.example.atlmovaapp.model.enum


enum class MovieCategory(val apiValue: String, val displayName: String) {
    NOW_PLAYING("now_playing", "Now Playing Movies"),
    POPULAR("popular", "Popular Movies"),
    TOP_RATED("top_rated", "Top Rated Movies"),
    UPCOMING("upcoming", "Upcoming Movies");

    companion object {
        fun fromApiValue(value: String): MovieCategory {
            return values().firstOrNull { it.apiValue == value } ?: POPULAR
        }
    }
}