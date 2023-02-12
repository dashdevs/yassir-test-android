package com.ddroznik.hilt_mvvm_compose_movie.domain.model.moviedetail

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetail(
    var adult: Boolean,
    var backdrop_path: String? = "",
    var budget: Int,
    @Ignore
    var genres: List<Genre>? = null,
    var homepage: String,
    @PrimaryKey
    var id: Int,
    var imdb_id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String? = "empty_path",
    @Ignore
    var production_companies: List<ProductionCompany>? = null,
    @Ignore
    var production_countries: List<ProductionCountry>? = null,
    var release_date: String,
    var revenue: Int,
    var runtime: Int,
    @Ignore
    var spoken_languages: List<SpokenLanguage>? = null,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
) {
    constructor() : this(
        false,
        "",
        0,
        null,
        "",
        0,
        "",
        "",
        "",
        "",
        0.0,
        "",
        null,
        null,
        "",
        0,
        0,
        null,
        "",
        "",
        "",
        false,
        0.0,
        0
    )
}