package com.oucs.movieappkotlin.ui.viewmodel

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oucs.movieappkotlin.common.MovieApp
import com.oucs.movieappkotlin.repository.MovieRepository
import com.oucs.movieappkotlin.retrofit.models.Movie
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {
    private val movieRepository:MovieRepository = MovieRepository()
    val popularMovies:MutableLiveData<List<Movie>> = MutableLiveData()

    @SuppressLint("NullSafeMutableLiveData")
    fun loadPopularMovies(){
        viewModelScope.launch {
            val popular = movieRepository.getPopularMovies()
            if (!popular.isNullOrEmpty()){
                popularMovies.value = popular
            }
            else{
                Toast.makeText(MovieApp.instance,"Algo salio mal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}