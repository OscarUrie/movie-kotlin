package com.oucs.movieappkotlin.repository

import android.util.Log
import com.oucs.movieappkotlin.retrofit.MovieNetWork
import com.oucs.movieappkotlin.retrofit.TheMovieDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository {
    private val theMovieDBService:TheMovieDBService = MovieNetWork.getTheMovieDBService()
    suspend fun getPopularMovies() = withContext(Dispatchers.IO){
        try {
            val response = theMovieDBService.getPopularMovies()
            return@withContext response.body()?.results
        }
        catch (e:Exception){
            Log.e("Retrofit","${e.printStackTrace()}")
            return@withContext null
        }
    }
}