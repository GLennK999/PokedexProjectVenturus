package com.example.ppiflutter.pokedexprojectventurus.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun listPokemons(@Query("limit") limit: Int): Call<PokemonsApiResult>
    //Ex https://pokeapi.co/api/v2/pokemon?limit=1327

    @GET("pokemon/{number}")
    fun getPokemon(@Path("number")number: Int): Call<PokemonApiResult>
    //Ex https://pokeapi.co/api/v2/pokemon/1
}