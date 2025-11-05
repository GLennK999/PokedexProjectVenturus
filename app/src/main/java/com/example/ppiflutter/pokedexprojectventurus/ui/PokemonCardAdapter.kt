package com.example.ppiflutter.pokedexprojectventurus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.ppiflutter.pokedexprojectventurus.databinding.CardPokemonBinding
import com.example.ppiflutter.pokedexprojectventurus.databinding.TypeIconBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

class PokemonCardAdapter(
    private var pokemons: List<PokemonModel>,
    private val onItemClicked: (PokemonModel) -> Unit)
    : RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = CardPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.run{
            //imagePokeCard.setImageResource(pokemon.imageUrl)
            pokeNumberCardText.text = pokemon.formattedDexNumber
            pokeNameCardText.text = pokemon.name

            val colors = mapOf<String,String>(
                "grass" to "#78C850",
                "fire" to "#F08030",
                "water" to "#6890F0",
                "electric" to "#F8D030",
                "psychic" to "#F85888",
                "ice" to "#98D8D8",
                "dragon" to "#7038F8",
                "dark" to "#705848",
                "fairy" to "#EE99AC",
                "normal" to "#A8A878",
                "fighting" to "#C03028",
                "poison" to "#A040A0",
                "ground" to "#E0C068",
                "flying" to "#A890F0",
                "bug" to "#A8B820",
                "rock" to "#B8A038",
                "ghost" to "#705898",
                "steel" to "#B8B8D0"
            )

            for (i in pokemon.types){
                val typeIcon = TypeIconBinding.inflate(LayoutInflater.from(root.context))
                typeIcon.pokeTypeIcon.text = i
                typeIcon.pokeTypeIcon.setBackgroundColor(colors[i]?.toColorInt() ?: "#FFFFFF".toColorInt())
                pokeCardLayoutTypes.addView(typeIcon.root)
            }

        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
    inner class PokemonViewHolder(val binding: CardPokemonBinding): RecyclerView.ViewHolder(binding.root){

    }


}