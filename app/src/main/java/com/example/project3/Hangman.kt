package com.example.project3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController

class Hangman : Fragment() {
    var word = ""
    lateinit var game: TextView
    private var timesWrong = 0
    private lateinit var head: ImageView
    private lateinit var hair: ImageView
    private lateinit var leftArm: ImageView
    private lateinit var rightArm: ImageView
    private lateinit var leftLeg: ImageView
    private lateinit var rightLeg: ImageView
    private lateinit var body: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        head = view.findViewById(R.id.head)
        hair = view.findViewById(R.id.hair)
        leftArm = view.findViewById(R.id.leftArm)
        leftLeg = view.findViewById(R.id.leftLeg)
        rightArm = view.findViewById(R.id.rightArm)
        rightLeg = view.findViewById(R.id.rightLeg)
        body = view.findViewById(R.id.body)
        head.visibility = View.INVISIBLE
        hair.visibility = View.INVISIBLE
        body.visibility = View.INVISIBLE
        leftLeg.visibility = View.INVISIBLE
        rightLeg.visibility = View.INVISIBLE
        rightArm.visibility = View.INVISIBLE
        leftArm.visibility = View.INVISIBLE

        var guessLetter = ""
        game = view.findViewById<TextView>(R.id.game)
        val goHome = view.findViewById<Button>(R.id.hangHome)
        goHome.setOnClickListener {
            val navController: NavController = view.findNavController()
            navController.navigate(R.id.action_hangman_to_home)
        }
        val mainActivity = activity as? MainActivity
        word = mainActivity?.fileData?.random().toString()
        game.text = "_ ".repeat(word.length-1) + "_"
        val input = view.findViewById<EditText>(R.id.input)
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                guessLetter = input.text.toString().uppercase()
                if(guessLetter.isNotEmpty()) {
                    isLetterCorrect(guessLetter)
                    if (timesWrong == 1) {
                        head.visibility = View.VISIBLE
                    } else if (timesWrong == 2) {
                        body.visibility = View.VISIBLE
                    } else if (timesWrong == 3) {
                        hair.visibility = View.VISIBLE
                    } else if (timesWrong == 4) {
                        leftArm.visibility = View.VISIBLE
                    } else if (timesWrong == 5) {
                        rightArm.visibility = View.VISIBLE
                    } else if (timesWrong == 6) {
                        leftLeg.visibility = View.VISIBLE
                    } else if (timesWrong == 7) {
                        rightLeg.visibility = View.VISIBLE
                    }else if(timesWrong>7){
                        input.isEnabled = false

                    }
                    input.text.clear()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangman, container, false)
    }

    fun isLetterCorrect(string: String) {
        val listOfLetters = word.map { it.toString() }
        val currentState = game.text.toString().split(" ").toMutableList()
        var isCorrect = false

        for (i in listOfLetters.indices) {
            if (listOfLetters[i] == string) {
                currentState[i] = string
                isCorrect = true
            }
        }
        if(!isCorrect){
            timesWrong++
        }
        game.text = currentState.joinToString(" ")
    }


}