package com.example.theoneapp.ui.characters.characterQuotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.R
import com.example.theoneapp.model.Quote

class CharacterQuotesAdapter() : RecyclerView.Adapter<CharacterQuotesAdapter.ViewHolder>() {

    private val quoteList = mutableListOf<Quote>()

    inner class ViewHolder(itemview: View) :
        RecyclerView.ViewHolder(itemview) {

        fun bind(quote: Quote) {
            itemView.findViewById<TextView>(R.id.tvQuote).text = quote.dialog
            itemView.findViewById<TextView>(R.id.tvQuoteMovie).text = quote.movieId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.quote_list_item, parent, false)
        return ViewHolder(view)
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