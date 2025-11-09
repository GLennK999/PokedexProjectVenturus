package com.example.ppiflutter.pokedexprojectventurus.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ppiflutter.pokedexprojectventurus.api.PokemonRepository
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import kotlinx.coroutines.Dispatchers
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

                val pokemonListApi =
                    pokemonRepository.listPokemons(9)?.results //BuscaListadePokemon

                if (pokemonListApi != null) {
                    val pokemonModels =
                        pokemonListApi.mapNotNull { pokemonResult -> //Chamadas em paralelo
                            withContext(Dispatchers.IO) {//IO Thread especfica para BDs e API
                                try {

                                    val pokeNumber =
                                        pokemonResult.url.split("/").last { it.isNotBlank() }
                                            .toIntOrNull() ?: 0

                                    val pokemonDetail =
                                        pokemonRepository.getPokemon(pokeNumber)
                                    pokemonDetail?.toPokemonModel(pokeNumber)
                                } catch (e: Exception) {
                                    Log.e(
                                        "Erro ViewModel",
                                        "Erro ao buscar ${pokemonResult.name}",
                                        e
                                    )
                                    null
                                }
                            }
                        }
                    _pokemonLiveDataList.value = pokemonModels

                    Log.d("Erro ViewModel", "Carregados ${pokemonModels.size} Pokémon")

                }
            } catch (e: Exception) {
                Log.e("Erro ViewModel", "Erro ao carregar Pokémon", e)
            }
        }

    }
}