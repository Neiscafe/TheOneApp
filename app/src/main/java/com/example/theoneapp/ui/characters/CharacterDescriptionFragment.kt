package com.example.theoneapp.ui.characters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.theoneapp.R
import com.example.theoneapp.databinding.FragmentCharacterDescriptionBinding
import com.example.theoneapp.model.Character
import com.google.android.material.bottomnavigation.BottomNavigationView


class CharacterDescriptionFragment : Fragment() {

    private var _binding: FragmentCharacterDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        screenBinding()
        setupClickListener()

    }

    private fun setupClickListener() {
        binding.btQuotes.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_characters_description_to_navigation_character_quotes)
        }
    }

    private fun screenBinding() {
        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getParcelable<Character>("characterBundle")

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
        }
        activity?.apply {
            findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    companion object {
//        fun newInstance(characterItem: Character) = Bundle().apply {
//            putParcelable("characterItem", characterItem)
//        }
//    }
}