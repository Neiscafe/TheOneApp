package com.example.theoneapp.ui.books

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.theoneapp.databinding.FragmentBooksBinding
import com.example.theoneapp.model.Book
import com.example.theoneapp.model.BookResponse
import com.example.theoneapp.ui.books.bookDescription.BookDescriptionActivity
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
        viewModel.booksResponse.observe(viewLifecycleOwner) {
            it?.let {
                setAdapter(it)
            }
        }
        viewModel.bookError.observe(viewLifecycleOwner) {
            apiError()
        }
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner) {
            it?.let {
                handleProgressBar(it)
            }
        }
    }

    private fun setAdapter(book: BookResponse) {

        val vpBooks = binding.vpBooks
        vpBooks.adapter = bookAdapter
        vpBooks.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        bookAdapter.append(book.docs)
        bookAdapter.setClickListener(object : BookAdapter.ClickListener {
            override fun onItemClick(bookItem: Book, position: Int) {
                val intent = Intent(requireActivity(), BookDescriptionActivity::class.java)
                intent.putExtra("bookItem", bookItem)
                startActivity(intent)
            }
        })

        val indicator = binding.ciBooks
        indicator.setViewPager(vpBooks)
    }

    private fun apiError() {
        Toast.makeText(
            this.requireContext(),
            "Ocorreu um erro durante o carregamento",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleProgressBar(state: BooksViewModel.State) {
        when (state) {
            BooksViewModel.State.LOADING -> binding.progressBar.visibility = View.VISIBLE
            BooksViewModel.State.LOADING_FINISHED -> binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}