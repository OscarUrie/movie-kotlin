package com.oucs.movieappkotlin.retrofit

import com.oucs.movieappkotlin.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TheMovieDBClient {
    private var theMovieDBService:TheMovieDBService
    private var retrofit: Retrofit
    companion object{
        var instance:TheMovieDBClient?=null
        get() {
            if (field==null){
                instance= TheMovieDBClient()
            }
            return field
        }
    }
    init {
        //incluir el interceptor para las peticiones
        val okHttpClientBuilder=OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        //Creacion de un cliente del interceptor
        val client=okHttpClientBuilder.build()

        //construir el objeto de retrofit
        retrofit=Retrofit.Builder()
                .baseUrl(Constantes.THE_MOVIE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        //instancia del servicio de retrofit

        theMovieDBService=retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService()=theMovieDBService
}