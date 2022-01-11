package com.oucs.movieappkotlin.retrofit

import com.oucs.movieappkotlin.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieNetWork {
    fun getTheMovieDBService(): TheMovieDBService {
        //incluir el interceptor para las peticiones
        val okHttpClientBuilder=OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        //Creacion de un cliente del interceptor
        val client=okHttpClientBuilder.build()

        //construir el objeto de retrofit
        val retrofit=Retrofit.Builder()
            .baseUrl(Constantes.THE_MOVIE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        //instancia del servicio de retrofit
        return retrofit.create(TheMovieDBService::class.java)
    }
}