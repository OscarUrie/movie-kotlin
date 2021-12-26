package com.oucs.movieappkotlin.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oucs.movieappkotlin.databinding.FragmentMovieListBinding
import com.oucs.movieappkotlin.retrofit.models.Movie
import com.oucs.movieappkotlin.ui.viewmodel.MovieViewModel

class MovieListFragment : Fragment() {

    private var columnCount = 2

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var popularMovies: MutableList<Movie> =ArrayList()

    private var resultBinding:FragmentMovieListBinding? = null
    private val binding get() = resultBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se obtiene una instancia del viewModel
        movieViewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        resultBinding = FragmentMovieListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovies()
        binding.movieListView.layoutManager = if (columnCount<=1){
            LinearLayoutManager(requireContext())
        }
        else {
            GridLayoutManager(requireContext(),columnCount)
        }
        binding.movieListView.adapter = movieAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultBinding = null
    }

    private fun loadMovies(){
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, {
            popularMovies.addAll(it)
            movieAdapter.setData(popularMovies)
        })
    }
}