package com.example.atlmovaapp.repository

import com.example.atlmovaapp.api.ApiServices
import com.example.atlmovaapp.local.mylist.MyListDao
import com.example.atlmovaapp.model.MovaListModel
import com.example.atlmovaapp.model.detail.MovieDetailResponse
import com.example.atlmovaapp.model.detail.flimCredits.CreditsResponse
import com.example.atlmovaapp.model.detail.reviews.ReviewResponse
import com.example.atlmovaapp.model.detail.trailers.TrailersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    val api: ApiServices,
    val dao: MyListDao
) {

    fun getPopularFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getPopularMovies())
    }.flowOn(Dispatchers.IO)

    fun getTopRatedFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getTopRatedMovies())
    }.flowOn(Dispatchers.IO)


    fun getUpcomingFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getUpcomingMovies())
    }.flowOn(Dispatchers.IO)

    fun getNowPlayingFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getNowPlayingMovies())
    }.flowOn(Dispatchers.IO)

    fun getMovieDetailFlow(id: Int): Flow<Response<MovieDetailResponse>> = flow {
        emit(api.getMovieDetail(movieId = id))
    }.flowOn(Dispatchers.IO)


    fun getMovieCreditsFlow(id: Int): Flow<Response<CreditsResponse>> = flow {
        emit(api.getMovieCredits(movieId = id))
    }.flowOn(Dispatchers.IO)


    fun getMovieTrailersFlow(id: Int): Flow<TrailersResponse> = flow {
        emit(api.getMovieTrailers(movieId = id))
    }.flowOn(Dispatchers.IO)

    fun getMovieReviewsFlow(id: Int): Flow<Response<ReviewResponse>> = flow {
        emit(api.getMovieReviews(movieId = id))
    }.flowOn(Dispatchers.IO)

    fun search(title: String): Flow<Response<MovaListModel>> = flow {
        emit(api.search(query = title))
    }.flowOn(Dispatchers.IO)


    suspend fun isMovieAdded(id: Int): Boolean {
        return dao.isMovieInList(id)
    }
}