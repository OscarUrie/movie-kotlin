package com.oucs.movieappkotlin.repository

import android.widget.Toast
import com.oucs.movieappkotlin.common.MovieApp
import com.oucs.movieappkotlin.retrofit.MovieNetWork
import com.oucs.movieappkotlin.retrofit.TheMovieDBService
import com.oucs.movieappkotlin.retrofit.models.Movie
import com.oucs.movieappkotlin.retrofit.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val theMovieDBService:TheMovieDBService = MovieNetWork.getTheMovieDBService()
    fun getPopularMovies(callback:(List<Movie>?) -> Unit)  {
        val response = theMovieDBService.getPopularMovies()
        response.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    callback(response.body()?.results)
                }
                else{
                    Toast.makeText(MovieApp.instance,"Algo salio mal",Toast.LENGTH_SHORT).show()
                    callback(null)
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(MovieApp.instance,"Error de conexion",Toast.LENGTH_SHORT).show()
                callback(null)
            }
        })
    }
}