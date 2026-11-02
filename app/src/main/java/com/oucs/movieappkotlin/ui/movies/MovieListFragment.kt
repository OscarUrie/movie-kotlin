package com.oucs.movieappkotlin.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.databinding.FragmentMovieListBinding
import com.oucs.movieappkotlin.ui.viewmodel.MovieViewModel

class MovieListFragment : Fragment(), AdapterListener {

    private var resultBinding:FragmentMovieListBinding? = null
    private val binding get() = resultBinding!!

    private val viewModel: MovieViewModel by activityViewModels()
    private val nowPlayingAdapter: MovieAdapter by lazy { MovieAdapter(this) }
    private val mostPopularAdapter: MovieAdapter by lazy { MovieAdapter(this) }
    private val topRatedAdapter: MovieAdapter by lazy { MovieAdapter(this) }
    private val upcomingAdapter: MovieAdapter by lazy { MovieAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        resultBinding = FragmentMovieListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        binding.nowPlayingView.adapter = nowPlayingAdapter
        binding.mostPopularView.adapter = mostPopularAdapter
        binding.topRatedView.adapter = topRatedAdapter
        binding.upcomingView.adapter = upcomingAdapter
        setupObservers()
        viewModel.loadNowPlayingAndMostPopularMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultBinding = null
    }

    override fun onClick(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        findNavController().navigate(R.id.action_navigation_movies_to_navigation_dashboard, bundle)
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainScrollView) { view, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, systemInsets.top, 0, systemInsets.bottom)
            return@setOnApplyWindowInsetsListener insets
        }
        binding.nowPlayingDivider.titleDivider.text = getString(R.string.only_in_theaters)
        binding.nowPlayingDivider.seeMoreButton.setOnClickListener {}
        binding.mostPopularDivider.titleDivider.text = getString(R.string.most_popular)
        binding.mostPopularDivider.seeMoreButton.setOnClickListener {}
        binding.topRatedDivider.titleDivider.text = getString(R.string.top_rated)
        binding.topRatedDivider.seeMoreButton.setOnClickListener {}
        binding.upcomingDivider.titleDivider.text = getString(R.string.coming_soon)
        binding.upcomingDivider.seeMoreButton.setOnClickListener {}
    }

    private fun setupObservers() {
        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            nowPlayingAdapter.submitList(it)
        }
        viewModel.mostPopularMovies.observe(viewLifecycleOwner) {
            mostPopularAdapter.submitList(it)
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            topRatedAdapter.submitList(it)
        }
        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingAdapter.submitList(it)
        }
        viewModel.apiState.observe(viewLifecycleOwner) { apiState ->
            binding.loader.root.isVisible = apiState.nowPlayingLoading()
            if (apiState.someError()) {
                Toast.makeText(
                    requireContext(),
                    R.string.something_went_wrong,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}