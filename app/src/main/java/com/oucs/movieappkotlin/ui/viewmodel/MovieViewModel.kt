package com.oucs.movieappkotlin.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oucs.movieappkotlin.repository.MovieRepository
import com.oucs.movieappkotlin.retrofit.models.Movie
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {
    private val movieRepository:MovieRepository = MovieRepository()
    val popularMovies:MutableLiveData<List<Movie>> = MutableLiveData()

    @SuppressLint("NullSafeMutableLiveData")
    fun loadPopularMovies(){
        viewModelScope.launch {
            movieRepository.getPopularMovies{ popular ->
                if (!popular.isNullOrEmpty()){
                    popularMovies.value = popular
                }
            }
        }
    }
}