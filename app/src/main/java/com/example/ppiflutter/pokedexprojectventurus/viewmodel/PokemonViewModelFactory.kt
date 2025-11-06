package com.example.ppiflutter.pokedexprojectventurus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PokemonViewModelFactory: ViewModelProvider.Factory {
    fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel() as T
    }

}