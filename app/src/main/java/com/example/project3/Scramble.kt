package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController


class Scramble : Fragment() {
    private var word = ""
    private lateinit var scrambled: TextView
    private lateinit var input: EditText
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scramble, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrambled = view.findViewById(R.id.scrambledWord)
        val goHome = view.findViewById<Button>(R.id.scrambleHome)
        goHome.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_scramble_to_home)
        }
        mainActivity = (activity as? MainActivity)!!
        word = mainActivity?.fileData?.random().toString()
        input = view.findViewById(R.id.scrambleGuess)



        val sArr = word.toCharArray()
        sArr.shuffle()
        word = StringBuilder().append(sArr).toString()
        scrambled.text = word
        Log.i("Scramble", "${scrambled.text}")
    }



}