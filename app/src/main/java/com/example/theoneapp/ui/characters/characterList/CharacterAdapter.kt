package com.example.theoneapp.ui.characters.characterList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theoneapp.databinding.CharacterListItemBinding
import com.example.theoneapp.model.character.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private val characterList = mutableListOf<Character>()
    private lateinit var clickListener: ClickListener

    interface ClickListener {
        fun onItemClick(characterItem: Character, position: Int)
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    inner class ViewHolder(val binding: CharacterListItemBinding, listener: ClickListener) :
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

        fun bind(characterItem: Character) {
            binding.tvName.text = characterItem.name

            binding.tvRace.text = characterItem.race
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterItem = characterList[position]
        holder.bind(characterItem)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun append(characterList: List<Character>) {
        this.characterList.clear()
        this.characterList.addAll(characterList)
        notifyDataSetChanged()
    }

    fun appendQuery(query: String = "", sortedList: List<Character> = emptyList()) {
        if (query.isEmpty()) {

            characterList.clear()
            characterList.addAll(sortedList)

        } else {

            val filteredList = sortedList.filter { character ->
                character.name.lowercase().contains(query.lowercase())
            }

            characterList.clear()
            characterList.addAll(filteredList)

        }
        notifyDataSetChanged()
    }
}