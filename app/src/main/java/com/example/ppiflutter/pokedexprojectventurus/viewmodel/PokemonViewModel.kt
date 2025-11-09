package com.example.ppiflutter.pokedexprojectventurus.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ppiflutter.pokedexprojectventurus.api.PokemonRepository
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokemonViewModel: ViewModel() {

    private var pokemonRepository = PokemonRepository
    private var _pokemonLiveDataList = MutableLiveData<List<PokemonModel>>()
    val pokemonList: LiveData<List<PokemonModel>> get() = _pokemonLiveDataList

    fun loadPokemons() {
        viewModelScope.launch {
            try {
                val pokemonQtd = pokemonRepository.listPokemons(1)?.count ?: 151
                val allPokemonResults = pokemonRepository.listPokemons(pokemonQtd)?.results

                if (allPokemonResults != null) {
                    val batchSize = 20
                    val batches = allPokemonResults.chunked(batchSize)
                    val accumulatedList = mutableListOf<PokemonModel>()

                    for (batch in batches) {
                        val batchModels = withContext(Dispatchers.IO) {
                            batch.map { pokemonResult ->
                                async {
                                    val pokeNumber = pokemonResult.url.split("/").last { it.isNotBlank() }.toIntOrNull() ?: 0
                                    try {
                                        val pokemonDetail = pokemonRepository.getPokemon(pokeNumber)
                                        pokemonDetail?.toPokemonModel(pokeNumber)
                                    } catch (e: Exception) {
                                        Log.e("Erro ViewModel", "Erro ao buscar ${pokemonResult.name}", e)
                                        null
                                    }
                                }
                            }.awaitAll()
                        }.filterNotNull()

                        accumulatedList.addAll(batchModels)
                        _pokemonLiveDataList.value = accumulatedList.toList()
                    }

                    Log.d("Erro ViewModel", "Carregados ${accumulatedList.size} Pokémon")
                }
            } catch (e: Exception) {
                Log.e("Erro ViewModel", "Erro ao carregar Pokémon", e)
            }
        }

    }
}





