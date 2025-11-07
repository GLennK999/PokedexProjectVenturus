package com.example.ppiflutter.pokedexprojectventurus.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.graphics.toColorInt
import androidx.core.view.isEmpty
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ppiflutter.pokedexprojectventurus.databinding.CardPokemonBinding
import com.example.ppiflutter.pokedexprojectventurus.databinding.TypeIconBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

class PokemonCardAdapter(private var pokemons: List<PokemonModel>,
    private val onItemClicked: (PokemonModel) -> Unit) //como posso usar esse onItemClicked para abrir a ProfileScreen?
    :RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

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




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = CardPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.onBind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }


    fun updatePokemonList(newPokemons: List<PokemonModel>) {
        pokemons = newPokemons
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(val binding: CardPokemonBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var clickedPokemon: PokemonModel

        init {
            binding.root.setOnClickListener {
                if(::clickedPokemon.isInitialized){
                    onItemClicked(clickedPokemon)
                }
            }
        }

        fun onBind (pokemon: PokemonModel){
            clickedPokemon = pokemon

            var typeIcon:TypeIconBinding
            val typeIconParams = LinearLayout.LayoutParams( 120, 40 ).also { it.setMargins(10, 10, 10, 10) }

            //Configuração das Informações do Card
            binding.run{
                Glide.with(root.context).load(pokemon.imageUrl).into(imagePokeCard)
                pokeNumberCardText.text = pokemon.formattedDexNumber
                pokeNameCardText.text = pokemon.name
                //Log.d("PokemonTypesInfo", "${pokemon.types}")
                pokeCardLayoutTypes.removeAllViews()
                for (i in pokemon.types){
                    typeIcon = TypeIconBinding.inflate(LayoutInflater.from(root.context))
                    typeIcon.pokeTypeIcon.text = i.uppercase()
                    typeIcon.pokeTypeIcon.setBackgroundColor(colors[i.lowercase()]?.toColorInt() ?: "#FFFFFF".toColorInt())
                    typeIcon.root.layoutParams = typeIconParams
                    pokeCardLayoutTypes.addView(typeIcon.root)
                }
            }
            // Fim do onBind
        }

        //Fim do ViewHolder
    }


}