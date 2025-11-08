package com.example.ppiflutter.pokedexprojectventurus.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ppiflutter.pokedexprojectventurus.api.PokemonRepository
import com.example.ppiflutter.pokedexprojectventurus.api.PokemonResult
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


class PokemonViewModel: ViewModel() {

    private var pokemonRepository = PokemonRepository
    private var _pokemonList = MutableLiveData<List<PokemonModel>>()
    val pokemonList: LiveData<List<PokemonModel>> get() = _pokemonList


    fun loadPokemons() {
        viewModelScope.launch {
            try {
                val pokemonListApi =
                    pokemonRepository.listPokemons(151)?.results //BuscaListadePokemon

                if (pokemonListApi != null) {
                    val pokemonModels = pokemonListApi.map { pokemonResult -> //Chamadas em paralelo
                        async {
                            try {
                                val pokemonDetail = pokemonRepository.getPokemon(pokemonResult.name)
                                pokemonDetail?.toPokemonModel()
                            } catch (e: Exception) {
                                Log.e("Erro ViewModel", "Erro ao buscar ${pokemonResult.name}", e)
                                null
                            }
                        }
                    }.awaitAll().filterNotNull()
                    _pokemonList.postValue(pokemonModels)
                    Log.d("Erro ViewModel", "Carregados ${pokemonModels.size} Pokémon")
                }
            } catch (e: Exception) {
                Log.e("Erro ViewModel", "Erro ao carregar Pokémon", e)
            }
        }

    }

    fun getCurrentPokemonList(): List<PokemonModel> {
        loadPokemons() //Está carregando 0 Pokémons
        if (_pokemonList.value != null){
            return _pokemonList.value!!
        }else{
            return emptyList()
        }
    }
}