package com.oucs.movieappkotlin.repository


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.oucs.movieappkotlin.common.MyApp
import com.oucs.movieappkotlin.retrofit.TheMovieDBClient
import com.oucs.movieappkotlin.retrofit.TheMovieDBService
import com.oucs.movieappkotlin.retrofit.models.Movie
import com.oucs.movieappkotlin.retrofit.models.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieDBService:TheMovieDBService?=null
    var theMovieDBClient:TheMovieDBClient?=null
    var popularMovies:MutableLiveData<List<Movie>>?=null

    init {
        theMovieDBClient=TheMovieDBClient.instance
        theMovieDBService= theMovieDBClient?.getTheMovieDBService()
        popularMovies=createPopularMovies()
    }

    private fun createPopularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies==null){
            popularMovies=MutableLiveData<List<Movie>>()
        }
        //Aqui se hace la peticion del call

        val call: Call<MoviesResponse>?=theMovieDBService?.getPopularMovies()
        call?.enqueue(object :Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    popularMovies?.value= response.body()?.results
                }
                else{
                    Toast.makeText(MyApp.instance,"Algo salio mal",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance,"Error de conexion",Toast.LENGTH_SHORT).show()
            }

        })

        return popularMovies
    }
}