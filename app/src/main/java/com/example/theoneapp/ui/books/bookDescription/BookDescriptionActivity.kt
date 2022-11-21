package com.example.theoneapp.ui.books.bookDescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theoneapp.databinding.ActivityBookDescriptionBinding
import com.example.theoneapp.model.Book
import com.example.theoneapp.model.ChapterResponse
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
        initObserver()


    }

    private fun initObserver() {
        viewModel.bookChapterResponse.observe(this, Observer {
            it?.let {
                setAdapter(it)
            }
        })
        viewModel.bookChapterError.observe(this, Observer {
            apiError()
        })
        viewModel.loadingStateLiveData.observe(this, Observer {
            handleProgressBar(it)
        })
    }

    private fun handleProgressBar(state: BookDescriptionViewModel.State) {
        when(state){
           BookDescriptionViewModel.State.LOADING -> binding.progressBar.visibility = View.VISIBLE
           BookDescriptionViewModel.State.LOADING_FINISHED -> binding.progressBar.visibility = View.GONE
        }
    }

    private fun apiError() {
        Toast.makeText(
            this,
            "Loading failed",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setAdapter(chapterResponse: ChapterResponse) {
        binding.rvChapterList.adapter = bookDescriptionAdapter
        binding.rvChapterList.layoutManager = LinearLayoutManager(this)
        bookDescriptionAdapter.append(chapterResponse.docs)
    }
}