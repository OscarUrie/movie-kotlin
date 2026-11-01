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
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Int,
    val profile_path: String,
    val cast_id: Long,
    val character: String,
    val credit_id: String,
    val order: Int,
)