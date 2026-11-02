package com.oucs.movieappkotlin.retrofit.models

/**
 * Movie cast response
 *
 * @property id movie Id
 * @property cast Cast List
 */
data class MovieCastResponse(
    val id: Int,
    val cast: List<Cast>
    //crew: Crew[]
)

/**
 * Cast
 */
data class Cast(
    val id: Int,
    val character: String,
    val name: String,
    val original_name: String,
    val profile_path: String
)