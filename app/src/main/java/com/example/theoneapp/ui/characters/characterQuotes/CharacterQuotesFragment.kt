package com.example.theoneapp.ui.characters.characterQuotes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theoneapp.R
import com.example.theoneapp.databinding.FragmentCharacterQuotesBinding
import com.example.theoneapp.model.QuoteResponse
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterQuotesFragment : Fragment() {
    private val viewModel by viewModel<CharacterQuotesViewModel>()
    private var _binding: FragmentCharacterQuotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var quotesAdapter: CharacterQuotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterQuotesBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_character_quotes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quotesAdapter = CharacterQuotesAdapter()
        val result = arguments?.getString("characterId")
        viewModel.retrieveCharacterQuotes(result)
        Log.e(TAG, "onViewCreated: "+result)
        screenBinding()

    }

    private fun screenBinding() {
        initObserver()
    }

    private fun initObserver() {
        viewModel.characterQuotesResponse.observe(viewLifecycleOwner) {
            it.let {
                setAdapter(it!!)
            }
        }
        viewModel.characterQuotesError.observe(viewLifecycleOwner) {
            apiError()
        }
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner) {
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
            this.requireContext(),
            "Ocorreu um erro durante o carregamento",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setAdapter(quoteResponse: QuoteResponse) {
        binding.rvQuotesList.apply {
            adapter = quotesAdapter
            Log.e(TAG, "setAdapter2: "+quoteResponse.quoteData)
            quotesAdapter.append(quoteResponse.quoteData)
        }
    }
}