package com.example.theoneapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.R
import com.example.theoneapp.model.Book

class BookAdapter: RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val bookList: MutableList<Book> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bookName: String) {
            itemView.findViewById<TextView>(R.id.tvName).text = bookName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
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