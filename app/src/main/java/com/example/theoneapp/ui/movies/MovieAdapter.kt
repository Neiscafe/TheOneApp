package com.example.theoneapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.R
import com.example.theoneapp.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movieName: String) {
            itemView.findViewById<TextView>(R.id.tvName).text = movieName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieName = movieList[position].name
        holder.bind(movieName)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun append(movieList: List<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }
}