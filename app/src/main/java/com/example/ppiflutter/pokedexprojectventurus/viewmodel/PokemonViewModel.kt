package com.example.ppiflutter.pokedexprojectventurus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ppiflutter.pokedexprojectventurus.api.PokemonRepository
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

class PokemonViewModel: ViewModel() {

    var pokemonlist = MutableLiveData<List<PokemonModel?>>()

    init {
        Thread({
            loadPokemons()
        }).start()
    }

    private fun loadPokemons(){
        val pokemonApiResult= PokemonRepository.listPokemons()

        pokemonApiResult?.results?.let{

        }
    }

}