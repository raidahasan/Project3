package com.example.project3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var message: TextView
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
        message = view.findViewById(R.id.winOrLose)
        val goHome = view.findViewById<Button>(R.id.scrambleHome)
        goHome.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_scramble_to_home)
        }
        mainActivity = (activity as? MainActivity)!!
        word = mainActivity?.fileData?.random().toString()
        input = view.findViewById(R.id.scrambleGuess)
        var guessWord = ""
        input.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                guessWord = input.text.toString().uppercase()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (guessWord !== word) {
                    input.isEnabled = false
                    message.text = "Wrong, try again!"
                    message.postDelayed({
                        message.text = ""
                    }, 1000)
                    input.text.clear()
                } else {
                    input.isEnabled = false
                    message.text = "Guessed it!"
                    message.postDelayed({
                        message.text = ""
                        reset()
                    }, 1000)
                }
            }
        })

        val sArr = word.toCharArray()
        sArr.shuffle()
        word = StringBuilder().append(sArr).toString()
        scrambled.text = word
        Log.i("Scramble", "${scrambled.text}")
    }

    fun reset() {
        word = ""
        scrambled = ""
        input.text.clear()
        message = ""
        mainActivity: MainActivity
        input.isEnabled = true
    }

}