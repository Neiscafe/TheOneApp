package com.example.theoneapp.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.R
import com.example.theoneapp.databinding.ListItemBinding
import com.example.theoneapp.model.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private val characterList = mutableListOf<Character>()
    private lateinit var clickListener: ClickListener

    interface ClickListener {
        fun onItemClick(characterItem: Character, position: Int)
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    inner class ViewHolder(binding: ListItemBinding, listener: ClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    listener.onItemClick(
                        characterList[bindingAdapterPosition],
                        bindingAdapterPosition
                    )
                }
            }
        }

        fun bind(characterName: String) {
            itemView.findViewById<TextView>(R.id.tvName).text = characterName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterName = characterList[position].name
        holder.bind(characterName)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun append(characterList: List<Character>) {
        this.characterList.clear()
        this.characterList.addAll(characterList)
        notifyDataSetChanged()
    }

}