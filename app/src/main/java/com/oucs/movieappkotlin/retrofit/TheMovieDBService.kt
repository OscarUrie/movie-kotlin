package com.oucs.movieappkotlin.retrofit

import com.oucs.movieappkotlin.retrofit.models.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {
    @GET("movie/popular")
    fun getPopularMovies():Call<MoviesResponse>
}