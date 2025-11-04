package com.example.ppiflutter.pokedexprojectventurus.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ppiflutter.pokedexprojectventurus.R
import com.example.ppiflutter.pokedexprojectventurus.databinding.ActivityMainBinding
import com.example.ppiflutter.pokedexprojectventurus.databinding.SplashScreenBinding

//Criar os xmls, com base nele criar a API, já testar o view model pra checar se por ele consigo mudar as informações do negócio, depois testar as infos da API,
// Procurar uma forma viável de pegar todas as informações necessarias para a Lista de Pokémons e passar através dela pras views
    class MainActivity : AppCompatActivity() {

        //implementação do viewModel

        //private lateinit var binding: ActivityMainBinding
        private lateinit var binding: SplashScreenBinding // defini ele primeiro para que mostre a splash screen mas não deu mto certo
        private lateinit var navController: NavController

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = SplashScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)
            enableEdgeToEdge()
            applyInsets()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, ActivityMainBinding::class.java) // referenciei aqui o Activity Main
                startActivity(intent)
                finish()
            },3000)
            /*
            navController =
                (supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment).run { navController }
            */
            //De onde vem esse navHostFragmentContainer?

        }

        override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

        private fun applyInsets(){
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
}