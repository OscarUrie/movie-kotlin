package com.oucs.movieappkotlin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oucs.movieappkotlin.repository.MovieRepository
import com.oucs.movieappkotlin.retrofit.models.Movie
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
}