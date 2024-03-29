package com.example.theoneapp.ui.characters.characterQuotes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.databinding.QuoteListItemBinding
import com.example.theoneapp.model.quote.Quote

class CharacterQuotesAdapter() : RecyclerView.Adapter<CharacterQuotesAdapter.ViewHolder>() {

    private val quoteList = mutableListOf<Quote>()

    inner class ViewHolder(val binding: QuoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(quote: Quote) {
            binding.tvQuote.text = "\"" + quote.dialog + "\""

            val movie = binding.tvQuoteMovie

            if (quote.movieId.equals("5cd95395de30eff6ebccde56")) {
                movie.text = "(The Lord of the Rings Series)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde57")) {
                movie.text = "(The Hobbit Series)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde58")) {
                movie.text = "(The Unexpected Journey)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde59")) {
                movie.text = "(The Desolation of Smaug)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde5a")) {
                movie.text = "(The Battle of the Five Armies)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde5b")) {
                movie.text = "(The Two Towers)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde5c")) {
                movie.text = "(The Fellowship of the Ring)"
            } else if (quote.movieId.equals("5cd95395de30eff6ebccde5d")) {
                movie.text = "(The Return of the King)"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quoteList[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    fun append(quotes: List<Quote>) {
        this.quoteList.clear()
        this.quoteList.addAll(quotes)
        notifyDataSetChanged()
    }

}