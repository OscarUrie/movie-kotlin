package com.oucs.movieappkotlin.ui.movies

/**
 * Class for capture service status
 *
 * @property nowPlayingStatus
 * @property mostPopularStatus
 * @property topRatedStatus
 * @property upcomingStatus
 */
data class ApiState(
    val nowPlayingStatus: ApiResult = ApiResult.Idle,
    val mostPopularStatus: ApiResult = ApiResult.Idle,
    val topRatedStatus: ApiResult = ApiResult.Idle,
    val upcomingStatus: ApiResult = ApiResult.Idle
) {

    fun nowPlayingLoading(): Boolean {
        return nowPlayingStatus == ApiResult.Idle && topRatedStatus == ApiResult.Idle
    }

    fun someError(): Boolean {
        if (nowPlayingStatus == ApiResult.Error) return true
        if (mostPopularStatus == ApiResult.Error) return true
        if (topRatedStatus == ApiResult.Error) return true
        if (upcomingStatus == ApiResult.Error) return true
        return false
    }
}


enum class ApiResult {
    Idle,
    Success,
    Error,
    Loading
}