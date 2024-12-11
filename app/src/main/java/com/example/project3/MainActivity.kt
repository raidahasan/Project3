package com.example.project3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Scanner


class MainActivity : AppCompatActivity() {
    lateinit var fileData: ArrayList<String>
    private lateinit var navController: NavController;
    private lateinit var appBarConfig: AppBarConfiguration;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val appBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(appBar)
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController= navHostFragment.navController
        val navGraph: NavGraph = navController.graph
        appBarConfig= AppBarConfiguration(navGraph)
        setupActionBarWithNavController(navController, appBarConfig)

        fileData = loadWordsFromFile()
        for(i in fileData){
            Log.i("load", i)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val success: Boolean = navController.navigateUp(appBarConfig)
        return success || super.onSupportNavigateUp()
    }



    private fun loadWordsFromFile(): ArrayList<String> {
        val words = ArrayList<String>()
        assets.open("words.txt").bufferedReader().useLines { lines ->
            lines.forEach { words.add(it) }
        }
        return words
    }

}