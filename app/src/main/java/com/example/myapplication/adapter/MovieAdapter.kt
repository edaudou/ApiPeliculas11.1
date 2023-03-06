package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.fragment.DetailsFragment
import com.example.myapplication.fragment.SearchFragmentDirections
import com.example.myapplication.model.Movie


class MovieAdapter(val context: Context) : ListAdapter<Movie, MovieAdapter.ItemViewHolder>(
    ItemViewHolder.MovieDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    //Recibimos los datos y los pasamos a los detalles
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tituloPelicula.text = item.title
        holder.description.text=item.description

        holder.itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(item.title,item.description,item.image)
            holder.itemView.findNavController().navigate(action)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloPelicula: TextView = itemView.findViewById(R.id.titulo_pelicula)
        val description:TextView=itemView.findViewById(R.id.descripcion_pelicula)

        //Añadimos diffcallback para advanced Recycler View
    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}}
