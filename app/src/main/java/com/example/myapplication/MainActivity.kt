package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        //Change to toolbar
        setSupportActionBar(binding.toolbar)
        //Cambiar texto navbar dependiendo del fragment
        //Get NavController
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.searchFragment) as NavHostFragment
        val navController= navHostFragment.navController
        //get appBarConfiguration
        val appBarConfiguration= AppBarConfiguration(navController.graph)

        //Asign to navigation
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)


    }
    //Creamos el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navmenu,menu)
        return true
    }
}