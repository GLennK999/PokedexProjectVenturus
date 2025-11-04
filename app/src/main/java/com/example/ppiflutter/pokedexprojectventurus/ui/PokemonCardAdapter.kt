package com.example.ppiflutter.pokedexprojectventurus.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ppiflutter.pokedexprojectventurus.R
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel

class PokemonCardAdapter(
    private var pokemons: List<PokemonModel>,
    private val onItemClick: (PokemonModel) -> Unit)
    : RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        /*
        val imagePokeCard= itemView.findViewById<ImageView>(R.id.imagePokeCard)
        val textNumberPokeCard = itemView.findViewById<ImageView>(R.id.textNumberPokeCard)
        val textNamePokeCard = itemView.findViewById<ImageView>(R.id.textNamePokeCard)
        val textType1PokeCard = itemView.findViewById<ImageView>(R.id.textType1PokeCard)
        val textType2PokeCard = itemView.findViewById<ImageView>(R.id.textType2PokeCard)
    */
        //.text = "0001"

    }


}