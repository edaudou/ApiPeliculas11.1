package com.example.myapplication.response

import com.example.myapplication.model.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    //Lista de Movie
    val results: List<Movie>?,
) {

}

