package com.oucs.movieappkotlin.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.oucs.movieappkotlin.databinding.FragmentMovieListBinding
import com.oucs.movieappkotlin.retrofit.models.Movie
import com.oucs.movieappkotlin.ui.viewmodel.MovieViewModel

class MovieListFragment : Fragment() {

    private var resultBinding:FragmentMovieListBinding? = null
    private val binding get() = resultBinding!!

    private val movieViewModel: MovieViewModel by activityViewModels()
    private val movieAdapter: MovieAdapter = MovieAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        resultBinding = FragmentMovieListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.loadPopularMovies()
        binding.movieListView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.movieListView.adapter = movieAdapter
        loadMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultBinding = null
    }

    private fun loadMovies(){
        movieViewModel.popularMovies.observe(viewLifecycleOwner, {
            Log.i("Retrofit","actualizando la vista")
            movieAdapter.setData(it)
        })
    }
}