package com.example.project3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Scanner


class MainActivity : AppCompatActivity() {
    lateinit var fileData: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val appBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(appBar)

        fileData = loadWordsFromFile()
        for(i in fileData){
            Log.i("load", i)
        }
    }



    private fun loadWordsFromFile(): ArrayList<String> {
        val words = ArrayList<String>()
        assets.open("words.txt").bufferedReader().useLines { lines ->
            lines.forEach { words.add(it) }
        }
        return words
    }

}