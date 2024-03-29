package com.example.theoneapp.ui.characters.characterDescription

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.theoneapp.databinding.ActivityCharacterDescriptionBinding
import com.example.theoneapp.model.character.Character
import com.example.theoneapp.ui.characters.characterQuotes.CharacterQuotesActivity


class CharacterDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        screenBinding()

    }

    private fun screenBinding() {
        val result = intent.getParcelableExtra<Character>("characterItem")

        with(binding) {
            tvCharacterName.text = result?.name

            if (result?.height.isNullOrEmpty()) {
                tvCharacterHeight.text = "Unknown"
            } else {
                tvCharacterHeight.text = result?.height
            }

            if (result?.race.isNullOrEmpty()) {
                tvCharacterRace.text = "Unknown"
            } else {
                tvCharacterRace.text = result?.race
            }

            if (result?.gender.isNullOrEmpty()) {
                tvCharacterGender.text = "Unknown"
            } else {
                tvCharacterGender.text = result?.gender
            }

            if (result?.birth.isNullOrEmpty()) {
                tvCharacterBirth.text = "Unknown"
            } else {
                tvCharacterBirth.text = result?.birth
            }

            if (result?.spouse.isNullOrEmpty()) {
                tvCharacterSpouse.text = "Unknown"
            } else {
                tvCharacterSpouse.text = result?.spouse
            }

            if (result?.death.isNullOrEmpty()) {
                tvCharacterDeath.text = "Unknown"
            } else {
                tvCharacterDeath.text = result?.death
            }

            if (result?.realm.isNullOrEmpty()) {
                tvCharacterRealm.text = "Unknown"
            } else {
                tvCharacterRealm.text = result?.realm
            }

            if (result?.height.isNullOrEmpty()) {
                tvCharacterHair.text = "Unknown"
            } else {
                tvCharacterHair.text = result?.hair
            }
        }

        binding.btQuotes.setOnClickListener {
            val intent = Intent(this, CharacterQuotesActivity::class.java)
            intent.putExtra("characterId", result?._id)
            startActivity(intent)
        }
    }

}