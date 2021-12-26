package com.oucs.movieappkotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oucs.movieappkotlin.repository.MovieRepository
import com.oucs.movieappkotlin.retrofit.models.Movie

class MovieViewModel:ViewModel() {
    private var movieRepository:MovieRepository = MovieRepository()
    private var popularMovies:LiveData<List<Movie>>
    init {
        popularMovies= movieRepository.popularMovies!!

    }
    fun getPopularMovies():LiveData<List<Movie>>{
        return popularMovies
    }
}