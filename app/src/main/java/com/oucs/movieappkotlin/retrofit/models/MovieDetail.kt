package com.oucs.movieappkotlin.retrofit.models

data class MovieDetail(
    val id: Int,
    val imdb_id: String,
    val title: String,
    val adult: Boolean,
    val backdrop_path: String,
    val genres: List<Genre>,
    val homepage: String?,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val vote_average: Double,
    val vote_count: Int,
    val spoken_languages: List<SpokenLanguage>,
    //val production_companies: List<ProductionCompany>,
)

data class Genre(
    val id: Int,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

/*

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)



*/