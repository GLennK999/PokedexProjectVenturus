package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.navArgs

class PokeProfileFragment: Fragment() {
    private val args: PokeProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonModel = args.pokemonModel
        // Use o pokemonModel aqui
    }

}
