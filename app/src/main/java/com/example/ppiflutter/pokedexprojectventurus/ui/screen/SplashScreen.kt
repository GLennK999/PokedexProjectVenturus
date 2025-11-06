package com.example.ppiflutter.pokedexprojectventurus.ui.screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ppiflutter.pokedexprojectventurus.R

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.navigate_splashScreenFragment_to_pokeSearchFragment)
        },3000)
        val view = inflater.inflate(R.layout.splash_screen, container, false)
        return view
    }

}