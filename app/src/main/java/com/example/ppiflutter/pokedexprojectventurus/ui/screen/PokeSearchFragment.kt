package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ppiflutter.pokedexprojectventurus.ui.PokemonCardAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ppiflutter.pokedexprojectventurus.databinding.PokemonSearchBinding
import com.example.ppiflutter.pokedexprojectventurus.model.pokemonList

class PokeSearchFragment: Fragment() {
    private var _binding: PokemonSearchBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PokemonSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonCardAdapter = PokemonCardAdapter(
            pokemonList,
            {
                //implementar pra ir pro PokeProfile
            })

    }

    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //val pokeCardAdapter = PokemonCardAdapter(emptyList())
        //configureList()
        //loadPokeCards()
        //observePokeCards()
    }

    private fun configureList(pokeCardAdapter: PokemonCardAdapter){
        /*
        Adaptar:

        binding.recyclerTaskList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
         */
    }

         */

}
