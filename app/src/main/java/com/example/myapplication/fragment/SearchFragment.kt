package com.example.myapplication.fragment

import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.model.MovieViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R

import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.model.Movie

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        //Inicializar ViewModel
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        //Inicializar adapter
        movieAdapter = MovieAdapter(requireContext())
        binding.moviesRecyclerView.adapter = movieAdapter

        //Mapeamos la respuesta y convertimos la lista MovieResponse en objetos Movie
        viewModel.searchResults.observe(viewLifecycleOwner) { movieResponses ->
            if (movieResponses != null) {
                val movies = movieResponses.map { movieResponse ->
                    Movie(movieResponse.id, movieResponse.title,movieResponse.description,movieResponse.image)
                }
                movieAdapter.submitList(movies)
            }
        }


        // Añadir clickListener a el boton y mostrar toast si no rellenamos nada
        binding.searchButton.setOnClickListener {
            val searchTerm = binding.searchEditText.text.toString()
            if (searchTerm.isBlank()) {
                Toast.makeText(context, "Empty Search Term", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchMovies(searchTerm)
            }
        }
        binding.moviesRecyclerView.layoutManager=LinearLayoutManager(context)

        return binding.root
    }

}
