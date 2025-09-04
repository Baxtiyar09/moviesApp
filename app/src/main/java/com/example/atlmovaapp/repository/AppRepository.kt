package com.example.atlmovaapp.repository

import com.example.atlmovaapp.api.ApiServices
import com.example.atlmovaapp.model.MovaListModel
import com.example.atlmovaapp.model.Result
import com.example.atlmovaapp.model.detail.MovieDetailResponse
import com.example.atlmovaapp.model.detail.flimCredits.CreditsResponse
import com.example.atlmovaapp.model.detail.reviews.ReviewResponse
import com.example.atlmovaapp.model.detail.trailers.TrailersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    val api: ApiServices
) {

    fun getPopularFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getPopularMovies())
    }

    fun getTopRatedFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getTopRatedMovies())
    }


    fun getUpcomingFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getUpcomingMovies())
    }

    fun getNowPlayingFlow(): Flow<Response<MovaListModel>> = flow {
        emit(api.getNowPlayingMovies())
    }

    fun getMovieDetailFlow(id: Int): Flow<Response<MovieDetailResponse>> = flow {
        emit(api.getMovieDetail(movieId = id))
    }


    fun getMovieCreditsFlow(id: Int): Flow<Response<CreditsResponse>> = flow {
        emit(api.getMovieCredits(movieId = id))
    }


    fun getMovieTrailersFlow(id: Int): Flow<TrailersResponse> = flow {
        emit(api.getMovieTrailers(movieId = id))

    }

    fun getMovieReviewsFlow(id: Int): Flow<Response<ReviewResponse>> = flow {
        emit(api.getMovieReviews(movieId = id))
    }

    fun search(title: String) : Flow<Response<MovaListModel>> = flow {
        emit(api.search(query = title))
    }

}