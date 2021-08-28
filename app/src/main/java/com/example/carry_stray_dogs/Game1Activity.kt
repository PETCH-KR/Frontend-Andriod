package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game1Activity : AppCompatActivity(){

    var dialog : AlertDialog ?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game1)
        //액션바 제거
        var actionBar : ActionBar? = supportActionBar
        actionBar?.hide()

        var answerBtn = findViewById<ImageButton>(R.id.answer)
        var wrongBtn = findViewById<ImageButton>(R.id.wrong)
        var cancelBtn = findViewById<ImageButton>(R.id.cancelBtn)
        var question = findViewById<ImageView>(R.id.question)

        answerBtn.setOnClickListener(){
            answerBtn.setImageResource(R.drawable.game1_answercheck)
            wrongBtn.setImageResource(R.drawable.game1_false)
            Log.i("answerBtn: ","click")

            answerBtn.setImageResource(R.drawable.game1_answercheck)
            wrongBtn.setImageResource(R.drawable.game1_false)
            question.visibility = View.INVISIBLE

            Log.i("nextBtn: ","click")

            val builder = AlertDialog.Builder(this)
            val view: View = LayoutInflater.from(this).inflate(R.layout.activity_answer1, null)
            dialog = builder.create()
            dialog!!.setView(view)
            dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

            CoroutineScope(Main).launch {
                dialog!!.show()
                delay(3000)
                dialog!!.dismiss()
                changeView()
            }
        }
        wrongBtn.setOnClickListener(){

            CoroutineScope(Main).launch {
                answerBtn.setImageResource(R.drawable.game1_true)
                wrongBtn.setImageResource(R.drawable.game1_answerfalse)
                Log.i("wrongBtn: ","click")
                delay(1000)
                answerBtn.setImageResource(R.drawable.game1_answercheck)
                wrongBtn.setImageResource(R.drawable.game1_false)
                question.visibility = View.INVISIBLE
                Log.i("nextBtn: ","click")
            }

            val builder = AlertDialog.Builder(this)
            val view: View = LayoutInflater.from(this).inflate(R.layout.activity_answer1, null)
            dialog = builder.create()
            dialog!!.setView(view)
            dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

            CoroutineScope(Main).launch {
                dialog!!.show()
                delay(3000)
                dialog!!.dismiss()
                changeView()
            }
        }
        cancelBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*
            answerBtn.setImageResource(R.drawable.game1_answercheck)
            wrongBtn.setImageResource(R.drawable.game1_false)
            question.visibility = View.INVISIBLE

            Log.i("nextBtn: ","click")

           val builder = AlertDialog.Builder(this)
           val view: View = LayoutInflater.from(this).inflate(R.layout.activity_answer1, null)
           dialog = builder.create()
           dialog!!.setView(view)
           dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

            CoroutineScope(Main).launch {
                dialog!!.show()
                delay(3000)
                dialog!!.dismiss()
                changeView()
            }

        }

         */

    }
    fun changeView(){
        val intent = Intent(this, Game2Activity::class.java)
        startActivity(intent)
        finish()
    }
}