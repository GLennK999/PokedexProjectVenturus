package com.example.ppiflutter.pokedexprojectventurus.api

data class PokemonsApiResult (
    val count: Int,
    val previous: String?,
    val next: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val name: String,
    val types: List<PokemonTypeResult> //Tentar trocar pra lista de strings
) {
    data class PokemonTypeResult(
        val slot: Int,
        val type: String
    )

}