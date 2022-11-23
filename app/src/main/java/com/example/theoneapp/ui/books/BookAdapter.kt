package com.example.theoneapp.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theoneapp.R
import com.example.theoneapp.databinding.ImageListItemBinding
import com.example.theoneapp.model.Book
import com.example.theoneapp.model.Character
import com.example.theoneapp.ui.characters.characterList.CharacterAdapter

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val bookList: MutableList<Book> = mutableListOf()
    private lateinit var clickListener: ClickListener

    interface ClickListener {
        fun onItemClick(bookItem: Book, position: Int)
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    inner class ViewHolder(val binding: ImageListItemBinding, listener: ClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.let {
                it.setOnClickListener {
                    listener.onItemClick(
                        bookList[bindingAdapterPosition],
                        bindingAdapterPosition
                    )
                }
            }
        }

        fun bind(bookName: String) {
            with(bookName) {
                if (equals("The Fellowship Of The Ring")) {
                    Glide.with(itemView)
                        .load("https://i.pinimg.com/564x/19/0f/43/190f4337809264046f61d4b2e1b41356.jpg")
                        .into(binding.ivBookCover)
                } else if (equals("The Two Towers")) {
                    Glide.with(itemView)
                        .load("https://i.pinimg.com/736x/3e/6c/8e/3e6c8ef216142883ce6256345633169b.jpg")
                        .into(binding.ivBookCover)

                } else {
                    Glide.with(itemView)
                        .load("https://i.pinimg.com/736x/e7/bb/56/e7bb565eccdde151313f7efc1de3b85e.jpg")
                        .into(binding.ivBookCover)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ImageListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookName = bookList[position].name
        holder.bind(bookName)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun append(bookList: List<Book>) {
        this.bookList.clear()
        this.bookList.addAll(bookList)
        notifyDataSetChanged()
    }
}