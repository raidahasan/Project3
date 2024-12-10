package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner


class MainActivity : AppCompatActivity() {
    lateinit var fileData: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val appBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(appBar)

        fileData = getFileData("src/words")
        Log.i("load", fileData.toString())
    }


    fun getFileData(fileName: String?): ArrayList<String> {
        val fileData = ArrayList<String>()
        try {
            val f = File(fileName)
            val s = Scanner(f)
            while (s.hasNextLine()) {
                val line = s.nextLine()
                if (line != "") fileData.add(line)
            }
            return fileData
        } catch (e: FileNotFoundException) {
            return fileData
        }
    }
}