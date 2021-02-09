package com.oucs.movieappkotlin.retrofit

import com.oucs.movieappkotlin.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Se añade la api_key a cada solicitud desde el telefono
        val urlWithParameter=chain.request()
                .url
                .newBuilder()
                .addQueryParameter(Constantes.URL_PARAM_API_KEY,Constantes.MY_API_KEY)
                .addQueryParameter(Constantes.URL_PARAM_LANGUAGE,"es-Es")
                .build()
        var request=chain.request()
        request= request.newBuilder()
                .url(urlWithParameter)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .build()
        return chain.proceed(request)
    }
}