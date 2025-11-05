package com.example.ppiflutter.pokedexprojectventurus.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonModel(
    val dexNumber: Int,
    val name: String,
    val types: List<String>, // deve funcionar como lista de strings
    val height: Double, // Dividir o valor da API por 10 pra converter pra m
    val weight: Double, // Dividir o valor da API por 10 pra converter pra kg
    val abilities: List<String>
): Parcelable {
    val formattedDexNumber = dexNumber.toString().padStart(3,'0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedDexNumber.png"
}