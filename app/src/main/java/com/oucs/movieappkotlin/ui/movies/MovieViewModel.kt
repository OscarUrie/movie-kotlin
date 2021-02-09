package com.oucs.movieappkotlin.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oucs.movieappkotlin.repository.TheMovieDBRepository
import com.oucs.movieappkotlin.retrofit.models.Movie

class MovieViewModel:ViewModel() {
    private var theMovieDBRepository:TheMovieDBRepository = TheMovieDBRepository()
    private var popularMovies:LiveData<List<Movie>>
    init {
        popularMovies= theMovieDBRepository.popularMovies!!

    }
    fun getPopularMovies():LiveData<List<Movie>>{
        return popularMovies
    }
}