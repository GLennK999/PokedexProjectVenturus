package com.example.ppiflutter.pokedexprojectventurus.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PokemonService::class.java) //cria e implementa os metodos do PokemonService
    }

    fun listPokemons(limit: Int = 151): PokemonListApiResult? {
        val call = service.listPokemons(limit)
        return call.execute().body()
    }

    fun getPokemon(name: String): PokemonApiResult? {
        val call = service.getPokemon(name)
        return call.execute().body()
    }
}