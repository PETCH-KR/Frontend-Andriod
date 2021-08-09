package com.example.carry_stray_dogs

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game3Activity : AppCompatActivity(){

    var dialog : AlertDialog?=null
    var moveX = 0f
    var moveY = 0f

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game3)
        //액션바 제거
        var actionBar : ActionBar? = supportActionBar
        actionBar?.hide()

        var nextBtn = findViewById<ImageButton>(R.id.nextBtn)
        var dog = findViewById<ImageView>(R.id.dog)

        dog.setOnTouchListener{v , event->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    moveX= v.x - event.rawX
                    moveY= v.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    v.animate() .x(event.rawX + moveX) .y(event.rawY + moveY) .setDuration(0) .start()
                }
            }
            true
        }

        nextBtn.setOnClickListener(){

            var question = findViewById<ImageView>(R.id.question)
            var question2 = findViewById<TextView>(R.id.question2)
            question.setVisibility(View.INVISIBLE)
            question2.setVisibility(View.INVISIBLE)

            val builder = AlertDialog.Builder(this)
            val view: View = LayoutInflater.from(this).inflate(R.layout.activity_answer1, null)
            dialog = builder.create()
            dialog!!.setView(view)
            dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

            CoroutineScope(Dispatchers.Main).launch {
                dialog!!.show()
                delay(3000)
                dialog!!.dismiss()
                changeView()
            }

        }

    }
    fun changeView(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}