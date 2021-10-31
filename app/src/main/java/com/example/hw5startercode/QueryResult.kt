package com.example.hw5startercode

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QueryResult(
    val total: Int,
    val totalHits: Int,
    val hits: List<PictureQueryResult>
)

data class PictureQueryResult(
    val largeImageURL: String
)
