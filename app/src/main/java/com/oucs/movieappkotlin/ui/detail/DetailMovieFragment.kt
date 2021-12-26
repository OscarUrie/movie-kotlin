package com.oucs.movieappkotlin.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oucs.movieappkotlin.databinding.FragmentDetailMovieBinding

class DetailMovieFragment : Fragment() {
    private var resultBinding:FragmentDetailMovieBinding? = null
    private val binding get() = resultBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resultBinding = FragmentDetailMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultBinding = null
    }
}