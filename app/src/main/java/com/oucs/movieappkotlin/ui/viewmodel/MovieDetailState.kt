package com.oucs.movieappkotlin.ui.viewmodel

import com.oucs.movieappkotlin.ui.movies.ApiResult

data class MovieDetailState(
    val detailResult: ApiResult = ApiResult.Idle,
    val castResult: ApiResult = ApiResult.Idle
) {
    fun isLoading(): Boolean {
        return detailResult == ApiResult.Idle
    }

    fun someError(): Boolean {
        return detailResult == ApiResult.Error || castResult == ApiResult.Error
    }
}
