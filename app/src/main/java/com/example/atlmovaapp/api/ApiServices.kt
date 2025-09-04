package com.example.atlmovaapp.api

import com.example.atlmovaapp.model.MovaListModel
import com.example.atlmovaapp.model.detail.MovieDetailResponse
import com.example.atlmovaapp.model.detail.flimCredits.CreditsResponse
import com.example.atlmovaapp.model.detail.reviews.ReviewResponse
import com.example.atlmovaapp.model.detail.trailers.TrailersResponse
import com.example.atlmovaapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovaListModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovaListModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovaListModel>


    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovaListModel>


    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDetailResponse>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<CreditsResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): TrailersResponse

    @GET("search/movie")
    suspend fun search(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String
    ): Response<MovaListModel>


    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<ReviewResponse>
}