package com.example.theoneapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theoneapp.R
import com.example.theoneapp.databinding.FragmentMoviesBinding
import com.example.theoneapp.model.MovieResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MoviesViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter()
        viewModel.retrieveMovies()
        initObserver()
    }

    private fun initObserver() {
        viewModel.moviesResponse.observe(viewLifecycleOwner){
            it?.let {
                setAdapter(it)
            }
        }
        viewModel.movieError.observe(viewLifecycleOwner){
            apiError()
        }
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner){
            it?.let {
                handleProgressBar(it)
            }
        }
    }

    private fun handleProgressBar(state: MoviesViewModel.State) {
        when(state){
            MoviesViewModel.State.LOADING->binding.progressBar.visibility = View.VISIBLE
            MoviesViewModel.State.LOADING_FINISHED->binding.progressBar.visibility = View.GONE
        }
    }

    private fun apiError() {
        Toast.makeText(this.requireContext(), "Ocorreu um erro durante o carregamento", Toast.LENGTH_SHORT).show()
    }

    private fun setAdapter(movie: MovieResponse) {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(this.requireContext())
        adapter.append(movie.docs)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}