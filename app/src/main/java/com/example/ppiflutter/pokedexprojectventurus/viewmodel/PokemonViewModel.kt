package com.example.ppiflutter.pokedexprojectventurus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ppiflutter.pokedexprojectventurus.api.PokemonRepository
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private var _pokemonList = MutableLiveData<List<PokemonModel?>>()
    val pokemonList: LiveData<List<PokemonModel?>> get() = _pokemonList


    private fun loadPokemons(){

        viewModelScope.launch {//coroutine
            /*
            val pokemonApiNumbers = PokemonRepository.listPokemons()?.results?.map {
                it.url //mapear pra pegar os numeros

                //com os numeros eu faço o PokemonRepository.getPokemon(numero)
            }

             */

            //oom o nome consigo

        }

    }

}