package com.example.myapplication.model

import com.example.myapplication.service.ImdbApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel : ViewModel() {
    //Ponemos valor defecto a la apikey
   private val apiKey = "k_kocnoeq3"

    //Construimos moshi para adaptar el servicio
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    //Api service con la web
    private val apiService = Retrofit.Builder()
        .baseUrl("https://imdb-api.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ImdbApiService::class.java)

    private val _searchResults = MutableLiveData<List<Movie>?>()
    val searchResults: MutableLiveData<List<Movie>?> = _searchResults

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie





    fun searchMovies(query: String) {
        viewModelScope.launch {
            val response = apiService.searchMoviesByTitle(apiKey, query)
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map { movieResponse ->
                    Movie(movieResponse.id, movieResponse.title, movieResponse.description,movieResponse.image)
                }
                _searchResults.value = movies
            } else {
                _searchResults.value = emptyList()
            }
        }
    }



    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }
}
