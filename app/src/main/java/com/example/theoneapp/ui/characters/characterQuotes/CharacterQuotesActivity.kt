package com.example.theoneapp.ui.characters.characterQuotes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theoneapp.databinding.ActivityCharacterQuotesBinding
import com.example.theoneapp.model.QuoteResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterQuotesActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharacterQuotesViewModel>()
    private lateinit var binding: ActivityCharacterQuotesBinding
    private lateinit var quotesAdapter: CharacterQuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quotesAdapter = CharacterQuotesAdapter()
        val result = intent.getStringExtra("characterId")
        viewModel.retrieveCharacterQuotes(result)
        screenBinding()
    }

    private fun screenBinding() {
        initObserver()
    }

    private fun initObserver() {
        viewModel.characterQuotesResponse.observe(this) {
            it?.let {
                setAdapter(it)
            }
        }
        viewModel.characterQuotesError.observe(this) {
            apiError()
        }
        viewModel.loadingStateLiveData.observe(this) {
            it?.let {
                handleProgressBar(it)
            }
        }
    }

    private fun handleProgressBar(state: CharacterQuotesViewModel.State) {
        when (state) {
            CharacterQuotesViewModel.State.LOADING -> binding.progressBar.visibility = View.VISIBLE
            CharacterQuotesViewModel.State.LOADING_FINISHED -> binding.progressBar.visibility =
                View.GONE
        }
    }

    private fun apiError() {
        Toast.makeText(
            this,
            "Ocorreu um erro durante o carregamento",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setAdapter(quoteResponse: QuoteResponse) {
        val rvQuotesList = binding.rvQuotesList
        rvQuotesList.adapter = quotesAdapter
        rvQuotesList.layoutManager = LinearLayoutManager(this)
        quotesAdapter.append(quoteResponse.quoteData)
        Log.e(TAG, "setAdapter:"+quoteResponse.quoteData)
    }
}