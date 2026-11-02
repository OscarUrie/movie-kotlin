package com.oucs.movieappkotlin.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.common.Constantes
import com.oucs.movieappkotlin.databinding.CastItemViewBinding
import com.oucs.movieappkotlin.retrofit.models.Cast

class MovieCastAdapter :
    ListAdapter<Cast, MovieCastAdapter.ViewHolder>(CastDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cast_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class CastDiffUtil : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(
            oldItem: Cast,
            newItem: Cast
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Cast,
            newItem: Cast
        ): Boolean = oldItem.id == newItem.id
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CastItemViewBinding.bind(view)

        fun bind(cast: Cast) {
            binding.castName.text = cast.name
            binding.castPicture.load(Constantes.URL_IMAGE + cast.profile_path) {
                crossfade(false)
            }
        }
    }
}