package com.oucs.movieappkotlin.retrofit

import com.oucs.movieappkotlin.retrofit.models.MovieCastResponse
import com.oucs.movieappkotlin.retrofit.models.MovieDetail
import com.oucs.movieappkotlin.retrofit.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getMostPopularMovies(): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MoviesResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") idMovie: Int): Response<MovieDetail>

    @GET("movie/{id}/credits")
    suspend fun getMovieCast(@Path("id") idMovie: Int): Response<MovieCastResponse>
}