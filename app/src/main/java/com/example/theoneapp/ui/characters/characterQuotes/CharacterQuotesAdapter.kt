package com.example.theoneapp.ui.characters.characterQuotes

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.R
import com.example.theoneapp.databinding.QuoteListItemBinding
import com.example.theoneapp.model.Character
import com.example.theoneapp.model.Quote

class CharacterQuotesAdapter() : RecyclerView.Adapter<CharacterQuotesAdapter.ViewHolder>() {

    private val quoteList = mutableListOf<Quote>()
    private lateinit var clickListener: CharacterQuotesAdapter.ClickListener

    interface ClickListener {
        fun onItemClick(quoteItem: Quote, position: Int)
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    inner class ViewHolder(binding: QuoteListItemBinding, listener: ClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    listener.onItemClick(
                        quoteList[bindingAdapterPosition],
                        bindingAdapterPosition
                    )
                }
            }
        }

        fun bind(dialog: String, movie: String) {
            itemView.findViewById<TextView>(R.id.tvQuote).text = dialog
            itemView.findViewById<TextView>(R.id.tvQuoteMovie).text = movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dialog = quoteList[position].dialog
        val movie = quoteList[position].movieId
        holder.bind(dialog, movie)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    fun append(quotes: List<Quote>) {
        Log.e(TAG, "append: " + quotes)
        Log.e(TAG, "append: " + quoteList)
        this.quoteList.clear()
        this.quoteList.addAll(quotes)
        Log.e(TAG, "append: " + quoteList)
        notifyDataSetChanged()
    }

}