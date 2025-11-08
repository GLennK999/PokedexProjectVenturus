package com.example.ppiflutter.pokedexprojectventurus.api

import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

data class PokemonApiResult(
    val name: String,
    val url: String?,
    val types: List<PokemonTypeResult>,
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
        val ability: Ability
    )

    data class Ability(
        val name:String,
        val url: String
    )

    fun toPokemonModel(): PokemonModel{
        val dexNumber = url?.let { urlString ->
            urlString.split("/").filter { it.isNotBlank() }.last().toIntOrNull()
        } ?: 0

        return PokemonModel(
            dexNumber = dexNumber,
            name = this.name,
            types = this.types.map { it.type.name },
            height = this.height / 10.0, // Converte para M
            weight = this.weight / 10.0, // Converte para kg
            abilities = this.abilities.map { it.ability.name }
        )
    }
} //As informações pra pegar da API e passar pro profile