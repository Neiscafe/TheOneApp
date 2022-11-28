package com.example.theoneapp.ui.movies.movieDescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.theoneapp.databinding.ActivityMovieDescriptionBinding
import com.example.theoneapp.model.movie.Movie

class MovieDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenBinding()



    }

    private fun screenBinding() {

        val result = intent.getParcelableExtra<Movie>("movieItem")

        binding.tvMovieName.text = result?.name
        binding.tvMovieRuntime.text = String.format("%d minutes", result?.runtimeInMinutes)
        binding.tvMovieBudget.text = String.format("%d million", result?.budgetInMillions)
        binding.tvMovieBoxOffice.text = String.format("%f million", result?.boxOfficeRevenueInMillions)
        binding.tvMovieNominations.text = String.format("%d", result?.academyAwardNominations)
        binding.tvMovieAwards.text = String.format("%d", result?.academyAwardWins)
        binding.tvMovieScore.text = String.format("%.1f", result?.rottenTomatoesScore)

    }
}