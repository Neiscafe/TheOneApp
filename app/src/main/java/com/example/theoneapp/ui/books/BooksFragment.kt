package com.example.theoneapp.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theoneapp.databinding.FragmentBooksBinding
import com.example.theoneapp.model.BookResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<BooksViewModel>()
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBooksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookAdapter = BookAdapter()
        viewModel.retrieveBooks()
        initObserver()
    }

    private fun initObserver() {
        viewModel.booksResponse.observe(viewLifecycleOwner){
            it?.let {
                setAdapter(it)
            }
        }
        viewModel.bookError.observe(viewLifecycleOwner){
            apiError()
        }
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner){
            it?.let {
                handleProgressBar(it)
            }
        }
    }

    private fun setAdapter(book: BookResponse) {
        binding.rvBookList.adapter = bookAdapter
        binding.rvBookList.layoutManager = LinearLayoutManager(this.requireContext())
        bookAdapter.append(book.docs)
    }

    private fun apiError() {
        Toast.makeText(this.requireContext(), "Ocorreu um erro durante o carregamento", Toast.LENGTH_SHORT).show()
    }

    private fun handleProgressBar(state: BooksViewModel.State) {
        when(state){
            BooksViewModel.State.LOADING-> binding.progressBar.visibility = View.VISIBLE
            BooksViewModel.State.LOADING_FINISHED-> binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}