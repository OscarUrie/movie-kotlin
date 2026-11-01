package com.oucs.movieappkotlin.repository

import android.util.Log
import com.oucs.movieappkotlin.retrofit.MovieNetWork
import com.oucs.movieappkotlin.retrofit.TheMovieDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Movie repository
 */
class MovieRepository {
    private val service:TheMovieDBService = MovieNetWork.getTheMovieDBService()

    /**
     * Get popular movies
     */
    suspend fun getPopularMovies() = withContext(Dispatchers.IO){
        try {
            val response = service.getPopularMovies()
            return@withContext response.body()?.results
        } catch (e: Exception) {
            Log.e("Retrofit", "${e.printStackTrace()}")
            return@withContext null
        }
    }


    /**
     * Get now playing movies
     */
    suspend fun getNowPlayingMovies() = withContext(Dispatchers.IO) {
        try {
            val response = service.getNowPlayingMovies()
            return@withContext response.body()?.results
        }
        catch (e:Exception){
            Log.e("Retrofit","${e.printStackTrace()}")
            return@withContext null
        }
    }

    /**
     * Get most popular movies
     */
    suspend fun getMostPopularMovies() = withContext(Dispatchers.IO) {
        try {
            val response = service.getMostPopularMovies()
            return@withContext response.body()?.results
        } catch (e: Exception) {
            Log.e("Retrofit", "${e.printStackTrace()}")
            return@withContext null
        }
    }

    /**
     * Get top rated movies
     */
    suspend fun getTopRatedMovies() = withContext(Dispatchers.IO) {
        try {
            val response = service.getTopRatedMovies()
            return@withContext response.body()?.results
        } catch (e: Exception) {
            Log.e("Retrofit", "${e.printStackTrace()}")
            return@withContext null
        }
    }

    /**
     * Get upcoming movies
     */
    suspend fun getUpcomingMovies() = withContext(Dispatchers.IO) {
        try {
            val response = service.getUpcomingMovies()
            return@withContext response.body()?.results
        } catch (e: Exception) {
            Log.e("Retrofit", "${e.printStackTrace()}")
            return@withContext null
        }
    }


    /**
     * Get movie detail
     *
     * @param id movie id
     */
    suspend fun getMovieDetail(id: Int) = withContext(Dispatchers.IO) {
        try {
            val response = service.getMovieDetail(id)
            return@withContext response.body()
        } catch (e: Exception) {
            Log.e("Retrofit", "${e.printStackTrace()}")
            return@withContext null
        }
    }

    /**
     * Get movie cast
     *
     * @param id movie id
     */
    suspend fun getMovieCast(id: Int) = withContext(Dispatchers.IO) {
        try {
            val response = service.getMovieCast(id)
            return@withContext response.body()?.cast
        } catch (e: Exception) {
            Log.e("Retrofit", "${e.printStackTrace()}")
            return@withContext null
        }
    }

}