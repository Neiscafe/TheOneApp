package com.example.theoneapp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.databinding.ListItemBinding
import com.example.theoneapp.databinding.MovieListItemBinding
import com.example.theoneapp.model.Movie
import com.bumptech.glide.Glide as Glide

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = mutableListOf<Movie>()
    private lateinit var clickListener: ClickListener

    inner class ViewHolder(val binding: MovieListItemBinding, listener: ClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    listener.onItemClick(
                        movieList[bindingAdapterPosition],
                        bindingAdapterPosition
                    )
                }
            }
        }

        fun bind(movie: Movie) {
            binding.tvName.text = movie.name
            binding.tvScore.text = String.format("Score: %.1f/100", movie.rottenTomatoesScore)
        }
    }

    interface ClickListener {
        fun onItemClick(movieItem: Movie, position: Int)
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
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