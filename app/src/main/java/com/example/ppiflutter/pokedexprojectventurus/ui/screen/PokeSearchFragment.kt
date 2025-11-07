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
import com.example.ppiflutter.pokedexprojectventurus.ui.PokemonCardAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppiflutter.pokedexprojectventurus.R
import com.example.ppiflutter.pokedexprojectventurus.databinding.PokemonSearchBinding
import com.example.ppiflutter.pokedexprojectventurus.model.PokemonModel
import com.example.ppiflutter.pokedexprojectventurus.model.pokemonList

class PokeSearchFragment: Fragment() {
    private var _binding: PokemonSearchBinding?= null
    private val binding get() = _binding!!

    private lateinit var pokemonCardAdapter: PokemonCardAdapter
    private val allPokemonList = pokemonList
    private val filteredPokemonList = mutableListOf<PokemonModel>()

    private var currentTypeFilter: String = "Todos"
    private var currentGenerationFilter: String = "Todas"


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
        //configureSpinners()
        setupSearchListener()
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        pokemonCardAdapter = PokemonCardAdapter(
            pokemonList, //filteredPokemonList,
            {
                navigationPokeProfile(it)
            })

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
                nameFilterPokemonList()
                hideKeyboard()
                true
            }  else {
                false
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nameFilterPokemonList()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    private fun nameFilterPokemonList(){
        val userInput = binding.searchEditText.toString().trim().lowercase()

        filteredPokemonList.clear()

        if(userInput.isEmpty()){
            filteredPokemonList.addAll(allPokemonList)
        } else {
            val filteredList = allPokemonList.filter { pokemon ->
                pokemon.name.lowercase().contains(userInput)
            }
            filteredPokemonList.addAll(filteredList)
        }
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
    }


    /*
    private fun configureSpinners(){
        //Filtro Tipos
        val typesSpinner = binding.pokeTypeSpinner
        val typeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,types
        )
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typesSpinner.adapter = typeAdapter

        //Filtro Gerações
        val genSpinner = binding.pokeGenSpinner
        val genAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            generations
        )
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genSpinner.adapter = genAdapter


        typesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentTypeFilter = types[position]
                applyFilters()
            }

            override fun onNothingSelected(parent: AdapterView<*>?){
                currentTypeFilter = "Todos"
                applyFilters()
            }
        }

        genSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentGenerationFilter = generations[position]
                applyFilters()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentGenerationFilter = "Todas"
                applyFilters()
            }
        }
    }

    fun applyFilters(){
        filteredPokemonList.clear()

        val filteredList = allPokemonList.filter { pokemon ->
            val typeMatch = if (currentTypeFilter == "Todos") {
                true
            } else {
                pokemon.types.any { it.equals(currentTypeFilter, ignoreCase = true) }
            }

            val generationMatch = if (currentGenerationFilter == "Todas") {
                true
            } else {
                pokemon.generation == currentGenerationFilter.toInt()
            }


            typeMatch && generationMatch
        }

        filteredPokemonList.addAll(filteredList)

        pokemonCardAdapter.notifyDataSetChanged()

    }

     */

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
