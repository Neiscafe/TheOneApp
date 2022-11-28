package com.example.theoneapp.ui.books.bookDescription

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.theoneapp.databinding.ChapterListItemBinding
import com.example.theoneapp.model.chapter.Chapter

class BookDescriptionAdapter : Adapter<BookDescriptionAdapter.ViewHolder>() {

    private val chapterList = mutableListOf<Chapter>()

    inner class ViewHolder(val binding: ChapterListItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(chapter: Chapter) {
            binding.tvChapterName.text = chapter.chapterName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChapterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    fun append(list: List<Chapter>){
        this.chapterList.clear()
        this.chapterList.addAll(list)
        notifyDataSetChanged()
    }
}