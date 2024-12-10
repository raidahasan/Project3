package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController

class Hangman : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var game = view.findViewById<TextView>(R.id.game)
        val goHome = view.findViewById<Button>(R.id.hangHome)
        goHome.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_hangman_to_home)
        }
        val word = "hangman"
        var i = 0
        while (i < word.length) {
            game.text = (game.text.toString() + "_")
            i++
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangman, container, false)
    }


}