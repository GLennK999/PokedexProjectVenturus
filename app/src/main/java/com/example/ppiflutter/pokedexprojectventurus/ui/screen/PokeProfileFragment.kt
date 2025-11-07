package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ppiflutter.pokedexprojectventurus.databinding.PokemonProfileBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

class PokeProfileFragment(): Fragment() {
    private val args: PokeProfileFragmentArgs by navArgs()
    private var _binding: PokemonProfileBinding? = null
    private val binding get() = _binding!!

    fun getPokemonFromArguments(): PokemonModel?{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelable("pokemonModel",PokemonModel::class.java)
        }else{
            @Suppress("DEPRECATION")
            arguments?.getParcelable("pokemonModel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PokemonProfileBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemon = getPokemonFromArguments()
        val abilitiesTxt = StringBuilder("Abilities: ")

        pokemon?.let { pocketMonster ->
            Glide.with(binding.root.context).load(pocketMonster.imageUrl).into(binding.pokeProfileImage)
            binding.pokeNameProfileText.text = pocketMonster.name
            binding.pokeNumberProfileText.text = pocketMonster.formattedDexNumber
            binding.pokeHeightProfileText.text = pocketMonster.height.toString()
            binding.pokeWeightProfileText.text = pocketMonster.weight.toString()

            for(i in pocketMonster.abilities){
                abilitiesTxt.append(" $i ")
            }

            binding.pokeAbilitiesText.text = abilitiesTxt.toString()

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
