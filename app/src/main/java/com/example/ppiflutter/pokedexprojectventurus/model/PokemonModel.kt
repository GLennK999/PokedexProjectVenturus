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
    val generation: Int
        get() = when (dexNumber) {
            in 1..151 -> 1
            in 152..251 -> 2
            in 252..386 -> 3
            in 387..493 -> 4
            in 494..649 -> 5
            in 650..721 -> 6
            in 722..809 -> 7
            in 810..905 -> 8
            in 906..1025 -> 9
            else -> 9
        }
}