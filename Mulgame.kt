package com.app.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.random.Random

class Mulgame : AppCompatActivity() {
    lateinit var textques : TextView
    lateinit var score : TextView
    lateinit var life : TextView
    lateinit var time : TextView
    lateinit var textanswer : EditText
    lateinit var Okbutton : Button
    lateinit var Nextbutton: Button
    private var res =0
    private var userinput =0
    var scores =0
    private var lifeline =3

    lateinit var timer : CountDownTimer
    private val starttime : Long = 60000
    var timeLeftInMilliSec : Long = starttime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_addgame)
        textques = findViewById(R.id.textQuestion)
        score = findViewById(R.id.textscorevalue)
        life = findViewById(R.id.textLifeValue)
        time = findViewById(R.id.textTimeValue)
        textanswer = findViewById(R.id.textAnswer)
        Okbutton = findViewById(R.id.buttonOk)
        Nextbutton = findViewById(R.id.buttonNext)

        mulGame()
        Okbutton.setOnClickListener()
        {
            val input  = textanswer.text.toString()
            if(input == "")
            {
                Toast.makeText(this,"Please Enter valid number",Toast.LENGTH_LONG).show()
            }
            else
            {
                pause()
                userinput = input.toInt()
                if(userinput == res)
                {
                    textques.text = "wow! Your answer is correct !"
                    scores = scores +10
                    score.text = scores.toString()


                }
                else
                {
                    textques.text = "Oops! your answer wrong. Give another try."
                    lifeline--
                    life.text=lifeline.toString()


                }
            }
        }
        Nextbutton.setOnClickListener()
        {
            if(lifeline ==0)
            {
                val intent = Intent(this,result::class.java)
                intent.putExtra("Score",scores)
                startActivity(intent)
                finish()

            }
            else
            {
                pause()
                reset()
                mulGame()

                textanswer.setText("")
            }

        }

    }
    fun mulGame() {
        var num1 = Random.nextInt(1,100)
        var num2 = Random.nextInt(2,100)

        textques.text = "$num1 x $num2"

        res = num1 * num2
        startTimer()

    }
    fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMilliSec,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMilliSec = millisUntilFinished
                update()
            }

            override fun onFinish() {
                pause()

                textques.text = "Oops! Time Over"
                lifeline--
                life.text = lifeline.toString().toString()
                reset()

                update()

            }

        }.start()
    }
    fun update()
    {
        val remainTime : Int = (timeLeftInMilliSec/1000).toInt()
        time.text = String.format(Locale.getDefault(),"%02d",remainTime)
    }
    fun pause(){
        timer.cancel()
    }
    fun reset()
    {
        timeLeftInMilliSec = starttime
        update()
    }

}