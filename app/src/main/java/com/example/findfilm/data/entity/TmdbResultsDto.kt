package com.example.findfilm.data.entity

data class TmdbResultsDto(
    val page: Int,
    val results: List<TmdbFilm>,
    val total_pages: Int,
    val total_results: Int
)