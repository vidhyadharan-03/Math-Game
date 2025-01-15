package com.app.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class result : AppCompatActivity() {
    lateinit var scoreVisit : TextView
    lateinit var exit : Button
    lateinit var playAgain : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
       scoreVisit= findViewById(R.id.textresScore)
        exit=findViewById(R.id.Exit)
        playAgain=findViewById(R.id.playagain)
      val scorevalue = intent.getIntExtra("Score" ,0)
        scoreVisit.text = scorevalue.toString()
        playAgain.setOnClickListener()
        {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        exit.setOnClickListener(){
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}