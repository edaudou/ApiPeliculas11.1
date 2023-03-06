package com.example.myapplication.fragment

import com.example.myapplication.model.MovieViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide


class DetailsFragment : Fragment() {

    val args:DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )
        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Safeargs
        val description=args.description
        val title=args.title
        val image=args.image

        //recibimos los argumentos y los ponemos en la vista
        binding.movieDesc.text = description
        binding.movieTitle.setText(title)
        binding.movieId.setText(id)
        //mediante glide podemos crear la imagen usando una url que el api esta devolviendo
        Glide.with(this)
            .load(image)
            .into(binding.movieImage)

        //Boton vuelve a el fragmento de busqueda
        val action=DetailsFragmentDirections.actionDetailsFragmentToSearchFragment()
        binding.buttonVolver.setOnClickListener{
            this.findNavController().navigate(action)
        }
    }
}
