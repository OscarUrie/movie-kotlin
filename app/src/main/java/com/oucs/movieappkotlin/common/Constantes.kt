package com.oucs.movieappkotlin.common

import com.oucs.movieappkotlin.BuildConfig

object Constantes {
    const val THE_MOVIE_API_URL="https://api.themoviedb.org/3/"
    const val MY_API_KEY = BuildConfig.API_KEY
    const val URL_IMAGE="http://image.tmdb.org/t/p/w500"

    const val URL_ORIGINAL_IMAGE = "https://image.tmdb.org/t/p/original"
    const val URL_PARAM_API_KEY="api_key"
    const val URL_PARAM_LANGUAGE="language"

    fun getRuntimeLabel(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime - (hours * 60)
        return "${hours}h ${minutes}m"
    }
}