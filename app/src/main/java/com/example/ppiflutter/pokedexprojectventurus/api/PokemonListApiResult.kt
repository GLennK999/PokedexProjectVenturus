package com.example.ppiflutter.pokedexprojectventurus.api

data class PokemonListApiResult (
    val count: Int,
    val previous: String?,
    val next: String?,
    val results: List<PokemonResult>
) //Como vem da API

data class PokemonResult(
    val name: String,
    val url: String
) //Informação que pego o nome do pokémon


