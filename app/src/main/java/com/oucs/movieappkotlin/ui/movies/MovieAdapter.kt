package com.oucs.movieappkotlin.ui.movies

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.common.Constantes
import com.oucs.movieappkotlin.retrofit.models.Movie
class MovieAdapter(
        private var moviesValues: MutableList<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesValues[position]
        holder.titleMovie.text=item.title
        holder.raitingMovie.text=item.vote_average.toString()
        holder.pictureMovie.load(Constantes.URL_IMAGE+item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_pelicula)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int = moviesValues.size
    @SuppressLint("NotifyDataSetChanged")
    fun setData(popularMovies: List<Movie>?) {
        moviesValues.clear()
        moviesValues.addAll(popularMovies!!)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleMovie:TextView=view.findViewById(R.id.tv_title_movie)
        val raitingMovie:TextView=view.findViewById(R.id.tv_raiting_movie)
        val pictureMovie:ImageView=view.findViewById(R.id.picture_movie)
        override fun toString(): String {
            return super.toString() + " '" + titleMovie.text + "'"
        }
    }
}