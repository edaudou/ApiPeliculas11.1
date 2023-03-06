package com.example.myapplication.service

import com.example.myapplication.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbApiService {
    @GET("/en/API/SearchTitle")
    //Añadimos la expresion del titulo y concatenamos el apiKey
    suspend fun searchMoviesByTitle(
        @Query("apiKey") apiKey: String,
        @Query("expression") title: String,
    ): Response<MovieResponse>
}

