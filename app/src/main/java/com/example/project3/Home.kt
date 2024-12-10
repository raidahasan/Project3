package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.findNavController

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val goHangman = view.findViewById<Button>(R.id.hangDir)
        goHangman.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_home_to_hangman)
        }
        val goWordle = view.findViewById<Button>(R.id.wordleDir)
        goWordle.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_home_to_wordle)
        }
        val goScramble = view.findViewById<Button>(R.id.scrambleDir)
        goScramble.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_home_to_scramble)
        }
    }
}