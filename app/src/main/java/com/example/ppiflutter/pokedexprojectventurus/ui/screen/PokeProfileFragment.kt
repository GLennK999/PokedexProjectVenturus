package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ppiflutter.pokedexprojectventurus.databinding.PokemonProfileBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

class PokeProfileFragment(val pokemon : PokemonModel): Fragment() {
    private val args: PokeProfileFragmentArgs by navArgs()
    private var _binding: PokemonProfileBinding? = null
    private val binding get() = _binding!!

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
        var abilitiestxt = "Abilities: "

        Glide.with(binding.root.context).load(pokemon.imageUrl).into(binding.pokeProfileImage)
        binding.pokeNameProfileText.text = pokemon.name
        binding.pokeNumberProfileText.text = pokemon.formattedDexNumber
        binding.pokeHeightProfileText.text = pokemon.height.toString()
        binding.pokeWeightProfileText.text = pokemon.weight.toString()

        for(i in pokemon.abilities){
            abilitiestxt += " "+i+" "
        }

        binding.pokeAbilitiesText.text = abilitiestxt

        //val pokemonModel = args.pokemonModel
        // Use o pokemonModel
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //binding.toolbarTask.menu.clear()
        _binding = null
    }



}
