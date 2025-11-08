package com.example.ppiflutter.pokedexprojectventurus.ui.screen;

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ppiflutter.pokedexprojectventurus.ui.PokemonCardAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppiflutter.pokedexprojectventurus.R
import com.example.ppiflutter.pokedexprojectventurus.databinding.PokemonSearchBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import com.example.ppiflutter.pokedexprojectventurus.model.pokemonList
import com.example.ppiflutter.pokedexprojectventurus.viewmodel.PokemonViewModel

class PokeSearchFragment: Fragment() {
    private var _binding: PokemonSearchBinding?= null
    private val binding get() = _binding!!

    private lateinit var pokemonCardAdapter: PokemonCardAdapter
    private val allPokemonList = pokemonList

    private var currentTypeFilter: String = "Todos"
    private var currentGenerationFilter: String = "Todas"

    val pokemonViewModel = PokemonViewModel()



    private val types = arrayOf("Todos", "normal", "fire", "water", "electric", "grass", "ice",
        "fighting", "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon",
        "dark", "steel", "fairy")

    private val generations = arrayOf("Todas", "1", "2", "3", "4", "5", "6", "7", "8","9")


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
        configureSpinners()
        setupSearchListener()
        configureRecyclerView()

    }


    /*
        pokemonCardAdapter = PokemonCardAdapter(
            pokemonList, //filteredPokemonList,
            {
                navigationPokeProfile(it)
            })

         */
    private fun configureRecyclerView() {
        //pokemonViewModel.getCurrentPokemonList()
        pokemonCardAdapter = PokemonCardAdapter(
            allPokemonList,
            {navigationPokeProfile(it)})

        binding.pokeCardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonCardAdapter
            setHasFixedSize(true)
        }
    }

    private fun navigationPokeProfile(pokemon: PokemonModel) {
        findNavController().navigate(
            PokeSearchFragmentDirections.actionPokeSearchFragmentToPokeProfileFragment(pokemon),
           )
    }

    private fun setupSearchListener(){
        binding.searchEditText.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterPokemonList()
                hideKeyboard()
                true
            }  else {
                false
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterPokemonList()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
    }



    private fun configureSpinners(){
        //Spinner Tipos
        val typeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.pokeTypeSpinner.adapter = typeAdapter

        //Spinner Gerações
        val generationAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, generations)
        generationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.pokeGenSpinner.adapter = generationAdapter

        //Listener Tipos
        binding.pokeTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                currentTypeFilter = types[position]
                filterPokemonList()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        //Listener Geração
        binding.pokeGenSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                currentGenerationFilter = generations[position]
                filterPokemonList()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }

        }

    }
    fun filterPokemonList(){
        val userInput = binding.searchEditText.text.toString().trim().lowercase()

        val filteredList = allPokemonList.filter { pokemon ->
            // Filtro por nome
            val matchesName = userInput.isEmpty() || pokemon.name.lowercase().contains(userInput)

            // Filtro por tipo
            val matchesType = currentTypeFilter == "Todos" || pokemon.types.any { type ->
                type.lowercase() == currentTypeFilter.lowercase()
            }

            // Filtro por geração
            val matchesGeneration = currentGenerationFilter == "Todas" || pokemon.generation == currentGenerationFilter.toInt()

            matchesName && matchesType && matchesGeneration
        }

        // Atualiza o adapter
        pokemonCardAdapter.updatePokemonList(filteredList)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
