package com.oucs.movieappkotlin.ui.viewmodel

import com.oucs.movieappkotlin.ui.movies.ApiResult


data class MovieVideoState(
    val url: String = "",
    val result: ApiResult = ApiResult.Idle
) {
    fun isLoading(): Boolean = result == ApiResult.Loading
}
