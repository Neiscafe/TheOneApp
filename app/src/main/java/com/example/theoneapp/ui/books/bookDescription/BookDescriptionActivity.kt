package com.example.theoneapp.ui.books.bookDescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.theoneapp.R
import com.example.theoneapp.databinding.ActivityBookDescriptionBinding
import com.example.theoneapp.model.Book
import com.example.theoneapp.ui.books.BookAdapter
import com.example.theoneapp.ui.books.BooksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDescriptionBinding
    private val viewModel by viewModel<BookDescriptionViewModel>()
    private lateinit var bookDescriptionAdapter: BookDescriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getParcelableExtra<Book>("bookItem")

        bookDescriptionAdapter = BookDescriptionAdapter()
        viewModel.retrieveBookChapters(result?._id)
        binding.tvBookTitle.text = result?.name


    }
}