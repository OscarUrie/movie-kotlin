package com.oucs.movieappkotlin.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.oucs.movieappkotlin.R
import com.oucs.movieappkotlin.common.Constantes
import com.oucs.movieappkotlin.databinding.FragmentDetailMovieBinding
import com.oucs.movieappkotlin.retrofit.models.Cast
import com.oucs.movieappkotlin.retrofit.models.MovieDetail
import com.oucs.movieappkotlin.ui.movies.ApiResult.Error
import com.oucs.movieappkotlin.ui.movies.ApiResult.Success
import com.oucs.movieappkotlin.ui.viewmodel.MovieViewModel

class DetailMovieFragment : Fragment() {
    private var resultBinding:FragmentDetailMovieBinding? = null
    private val binding get() = resultBinding!!

    private val viewModel: MovieViewModel by activityViewModels()
    private val movieCastAdapter: MovieCastAdapter by lazy { MovieCastAdapter() }
    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resultBinding = FragmentDetailMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupObservers()
        val movieId = arguments?.getInt("movie_id")
        movieId?.let { viewModel.loadMovieDetail(it) }
    }

    override fun onDestroyView() {
        viewModel.clearDetailDate()
        super.onDestroyView()
        resultBinding = null
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainScrollView) { view, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, 0, 0, systemInsets.bottom)
            binding.customToolbar.setPadding(0, systemInsets.top, 0, 0)
            return@setOnApplyWindowInsetsListener insets
        }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.favoriteButton.setOnClickListener {
            //TODO
        }
        binding.seeTrailer.setOnClickListener {
            viewModel.loadMovieVideos()
        }
        binding.loader.root.setOnClickListener { }
        binding.castListView.adapter = movieCastAdapter
        binding.categoryListView.adapter = categoriesAdapter
    }

    private fun setupObservers() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { detail ->
            detail?.let { bindMovieDetail(it) }
        }

        viewModel.movieCast.observe(viewLifecycleOwner) { cast ->
            cast?.let { bindMovieCast(it) }
        }
        viewModel.movieDetailState.observe(viewLifecycleOwner) { state ->
            binding.loader.root.isVisible = state.isLoading()
            if (state.someError()) {
                Toast.makeText(
                    requireContext(),
                    R.string.something_went_wrong,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewModel.trailerState.observe(viewLifecycleOwner) { state ->
            binding.loader.root.isVisible = state.isLoading()
            when (state.result) {
                Success if state.url.isNotEmpty() -> {
                    launchPlayerIntent(state.url)
                }

                Error -> {
                    Toast.makeText(
                        requireContext(),
                        R.string.something_went_wrong,
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }

    private fun bindMovieDetail(movieDetail: MovieDetail) {
        binding.movieTitle.text = movieDetail.title
        binding.movieDate.text = Constantes.getDateFormat(movieDetail.release_date)
        binding.movieDuration.text = Constantes.getRuntimeLabel(movieDetail.runtime)
        binding.movieDescription.text = movieDetail.overview
        binding.moviePoster.load(Constantes.URL_ORIGINAL_IMAGE + movieDetail.backdrop_path) {
            crossfade(true)
        }
        categoriesAdapter.submitList(movieDetail.genres)
    }

    private fun bindMovieCast(list: List<Cast>) {
        movieCastAdapter.submitList(list)
    }

    private fun launchPlayerIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}