package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ppiflutter.pokedexprojectventurus.ui.PokemonCardAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppiflutter.pokedexprojectventurus.R
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

        configureRecyclerView()


    }

    private fun configureRecyclerView() {
        val pokemonCardAdapter = PokemonCardAdapter(
            pokemonList,
            { pokemon ->
                // Implementar navegação para o perfil do Pokémon
                findNavController().navigate(
                    // Substitua pelo seu action ID
                    R.id.action_pokeSearchFragment_to_pokeProfileFragment, //substituir pelo binding
                    Bundle().apply {
                        putParcelable("pokemon", pokemon)

                        //implementar pra ir pro PokeProfile
                    })
            })

        binding.pokeCardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonCardAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
