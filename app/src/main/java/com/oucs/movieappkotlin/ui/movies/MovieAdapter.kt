package com.oucs.movieappkotlin.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.common.Constantes
import com.oucs.movieappkotlin.databinding.MovieItemBinding
import com.oucs.movieappkotlin.retrofit.models.Movie

class MovieAdapter(private val adapterListener: AdapterListener) :
    ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean = oldItem.id == newItem.id
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemBinding.bind(view)

        init {
            binding.pictureMovie.setOnClickListener {
                adapterListener.onClick(getItem(bindingAdapterPosition).id)
            }
        }

        fun bind(movie: Movie) {
            binding.pictureMovie.contentDescription = movie.title
            binding.pictureMovie.load(Constantes.URL_IMAGE + movie.poster_path) {
                crossfade(false)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
        }
    }
}