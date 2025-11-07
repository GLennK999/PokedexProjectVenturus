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
) //Informação que pego o numero do pokémon


data class PokemonApiResult(
    val name: String,
    val types: List<PokemonTypeResult>,//Tentar trocar pra lista de strings
    val abilities: List<PokemonAbilityResult>,
    val height: Int,
    val weight: Int
) {
    data class PokemonTypeResult(
        val slot: Int,
        val type: Type
    )

    data class Type(
        val name:String,
        val url: String
    )

    data class PokemonAbilityResult(
        val slot: Int,
        val name: Ability
    )

    data class Ability(
        val name:String,
        val url: String
    )

} //As informações pra pegar da API e passar pro profile