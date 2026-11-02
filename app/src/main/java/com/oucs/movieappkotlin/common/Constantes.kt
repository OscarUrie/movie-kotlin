package com.oucs.movieappkotlin.common

import android.annotation.SuppressLint
import com.oucs.movieappkotlin.BuildConfig
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Constantes {

    const val THE_MOVIE_API_URL="https://api.themoviedb.org/3/"
    const val MY_API_KEY = BuildConfig.API_KEY
    const val URL_IMAGE="http://image.tmdb.org/t/p/w500"

    const val URL_ORIGINAL_IMAGE = "https://image.tmdb.org/t/p/original"
    const val URL_PARAM_API_KEY="api_key"
    const val URL_PARAM_LANGUAGE="language"
    const val YOUTUBE = "https://www.youtube.com/watch?v="


    private val locale get() = Locale.getDefault()

    fun getLocale(): String {
        return when (locale.language) {
            "es" -> "es-${locale.country}"
            "pt" -> "pt-${locale.country}"
            else -> "en-US"
        }
    }

    fun getRuntimeLabel(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime - (hours * 60)
        return "${hours}h ${minutes}m"
    }

    @SuppressLint("DefaultLocale")
    fun getDateFormat(date: String): String {
        return try {
            val localDate = LocalDate.parse(date)
            return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } catch (_: Exception) {
            date
        }
    }
}