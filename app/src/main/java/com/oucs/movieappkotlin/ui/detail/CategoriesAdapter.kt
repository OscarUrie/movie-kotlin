package com.oucs.movieappkotlin.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.databinding.CategoryItemViewBinding
import com.oucs.movieappkotlin.retrofit.models.Genre

class CategoriesAdapter :
    ListAdapter<Genre, CategoriesAdapter.ViewHolder>(CategoryDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class CategoryDiffUtil : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean = oldItem.id == newItem.id
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CategoryItemViewBinding.bind(view)

        fun bind(genre: Genre) {
            binding.root.text = genre.name
        }
    }
}