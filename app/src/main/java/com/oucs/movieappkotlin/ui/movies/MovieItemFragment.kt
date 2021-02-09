package com.oucs.movieappkotlin.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.retrofit.models.Movie

/**
 * A fragment representing a list of Items.
 */
class MovieItemFragment : Fragment() {

    private var columnCount = 2

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieRecyclerViewAdapter: MyMovieRecyclerViewAdapter
    private var popularMovies: List<Movie> =ArrayList()

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                MovieItemFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        //Se obtiene una instancia del viewModel
        movieViewModel=ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_item_list, container, false)

        // Set the adapter
        movieRecyclerViewAdapter= MyMovieRecyclerViewAdapter(popularMovies)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                //movieRecyclerViewAdapter= MyMovieRecyclerViewAdapter(popularMovies,context)
                loadMovies()
                adapter = movieRecyclerViewAdapter
            }
        }
        return view
    }
    private fun loadMovies(){
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, {
            popularMovies=it
            movieRecyclerViewAdapter.setData(popularMovies)
        })
    }


}