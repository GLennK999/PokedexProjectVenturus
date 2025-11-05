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
                "grass" to "#FFFFFF",
                "normal" to "#FFFFFF"
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