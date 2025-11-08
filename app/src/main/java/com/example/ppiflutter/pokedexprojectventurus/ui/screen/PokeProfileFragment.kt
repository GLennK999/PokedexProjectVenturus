package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ppiflutter.pokedexprojectventurus.databinding.PokemonProfileBinding
import com.example.ppiflutter.pokedexprojectventurus.databinding.TypeIconBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import com.example.ppiflutter.pokedexprojectventurus.ui.screen.utils.colors

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
        var typeIcon:TypeIconBinding
        val typeIconParams = LinearLayout.LayoutParams( 180, 60 ).also { it.setMargins(10, 10, 10, 10) }


        pokemon?.let { pocketMonster ->
            Glide.with(binding.root.context).load(pocketMonster.imageUrl).into(binding.pokeProfileImage)
            binding.pokeNameProfileText.text = pocketMonster.name
            binding.pokeNumberProfileText.text = StringBuilder("N#${pocketMonster.formattedDexNumber}").toString()
            binding.pokeHeightProfileText.text = StringBuilder("Height: ${pocketMonster.height} M").toString()
            binding.pokeWeightProfileText.text = StringBuilder("Wight ${pocketMonster.weight} Kg").toString()

            for(i in pocketMonster.abilities){
                abilitiesTxt.append(" $i ")
            }

            binding.pokeAbilitiesText.text = abilitiesTxt.toString()

            binding.pokeProfileLayoutTypes.removeAllViews()
            for(i in pocketMonster.types){
                typeIcon = TypeIconBinding.inflate(LayoutInflater.from(context))
                typeIcon.pokeTypeIcon.text = i.uppercase()
                typeIcon.pokeTypeIcon.textSize = 18f
                typeIcon.pokeTypeIcon.setBackgroundColor(colors[i.lowercase()]?.toColorInt() ?: "#FFFFFF".toColorInt())
                typeIcon.root.layoutParams = typeIconParams
                binding.pokeProfileLayoutTypes.addView(typeIcon.root)
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
