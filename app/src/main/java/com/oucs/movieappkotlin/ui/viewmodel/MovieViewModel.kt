package com.oucs.movieappkotlin.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oucs.movieappkotlin.common.Constantes
import com.oucs.movieappkotlin.repository.MovieRepository
import com.oucs.movieappkotlin.retrofit.models.Cast
import com.oucs.movieappkotlin.retrofit.models.Movie
import com.oucs.movieappkotlin.retrofit.models.MovieDetail
import com.oucs.movieappkotlin.retrofit.models.MovieVideo
import com.oucs.movieappkotlin.ui.movies.ApiResult
import com.oucs.movieappkotlin.ui.movies.ApiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {
    private val movieRepository:MovieRepository = MovieRepository()
    val nowPlayingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val mostPopularMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val topRatedMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val apiState: MutableLiveData<ApiState> = MutableLiveData(ApiState())
    val movieDetail: MutableLiveData<MovieDetail?> = MutableLiveData(null)
    val movieCast: MutableLiveData<List<Cast>> = MutableLiveData(emptyList())

    val trailerState: MutableLiveData<MovieVideoState> = MutableLiveData(MovieVideoState())

    val movieDetailState: MediatorLiveData<MovieDetailState> =
        MediatorLiveData(MovieDetailState()).apply {
            addSource(movieDetail) {
                value = if (it != null) value?.copy(detailResult = ApiResult.Success)
                else value?.copy(detailResult = ApiResult.Idle)
            }
            addSource(movieCast) {
                value = if (it.isNotEmpty()) value?.copy(castResult = ApiResult.Success)
                else value?.copy(castResult = ApiResult.Idle)
            }
        }

    private lateinit var job: Job

    fun loadNowPlayingAndMostPopularMovies() {
        job = viewModelScope.launch {
            val result = movieRepository.getNowPlayingMovies()
            val result2 = movieRepository.getMostPopularMovies()
            result?.let { nowPlayingMovies.value = it }
            result2?.let { mostPopularMovies.value = it }
            apiState.value = ApiState(
                nowPlayingStatus = if (result == null) ApiResult.Error else ApiResult.Success,
                mostPopularStatus = if (result2 == null) ApiResult.Error else ApiResult.Success
            )
        }
        job.invokeOnCompletion {
            loadTopRatedAndUpcomingMovies()
        }
    }


    fun loadTopRatedAndUpcomingMovies() {
        viewModelScope.launch {
            val result = movieRepository.getTopRatedMovies()
            val result2 = movieRepository.getUpcomingMovies()
            result?.let { topRatedMovies.value = it }
            result2?.let { upcomingMovies.value = it }
            apiState.value = apiState.value?.copy(
                topRatedStatus = if (result == null) ApiResult.Error else ApiResult.Success,
                upcomingStatus = if (result2 == null) ApiResult.Error else ApiResult.Success
            )
        }
    }

    fun loadMovieDetail(id: Int) {
        viewModelScope.launch {
            movieDetailState.value = MovieDetailState()
            val result = movieRepository.getMovieDetail(id)
            val result2 = movieRepository.getMovieCast(id)
            result?.let { movieDetail.value = it }
            result2?.let { movieCast.value = it }
        }
    }

    fun clearDetailDate() {
        movieDetail.value = null
        movieCast.value = emptyList()
        trailerState.value = MovieVideoState()
    }

    fun loadMovieVideos() {
        viewModelScope.launch {
            val id = movieDetail.value?.id ?: return@launch
            trailerState.value = MovieVideoState(result = ApiResult.Loading)
            val result = movieRepository.getMovieVideos(id)
            if (result == null) {
                trailerState.value = MovieVideoState(result = ApiResult.Error)
                return@launch
            }
            val videoUrl = getFirstVideoUrl(result)
            trailerState.value = MovieVideoState(videoUrl, result = ApiResult.Success)
            trailerState.postValue(MovieVideoState())
        }
    }

    private fun getFirstVideoUrl(list: List<MovieVideo>?): String {
        if (list.isNullOrEmpty()) return ""
        val video = list
            .firstOrNull {
                it.type == "Trailer" && it.site == "YouTube" && it.key.isNotEmpty()
            } ?: return ""
        return Constantes.YOUTUBE + video.key
    }
}