package com.oucs.movieappkotlin.retrofit

import com.oucs.movieappkotlin.retrofit.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface TheMovieDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies():Response<MoviesResponse>
}