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
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game3Activity : AppCompatActivity(){

    var dialog : AlertDialog?=null
    var moveX = 0f
    var moveY = 0f
    var question : ImageView ?= null
    var question2 : TextView ?= null
    var img : ImageView ?= null
    var img2 : ImageView ?= null
    var dog : ImageView ?= null
    var dogInfo : TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game3)
        //액션바 제거
        var actionBar : ActionBar? = supportActionBar
        actionBar?.hide()

        var cancelBtn = findViewById<ImageButton>(R.id.cancelBtn)
        question = findViewById<ImageView>(R.id.question)
        question2 = findViewById<TextView>(R.id.question2)
        img = findViewById<ImageView>(R.id.imageView7)
        img2 = findViewById<ImageView>(R.id.imageView8)
        dog = findViewById<ImageView>(R.id.dog)
        dogInfo = findViewById<TextView>(R.id.dogInfo)
        var dog = findViewById<ImageView>(R.id.dog)

        dog.setOnTouchListener{v , event->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        moveX= v.x - event.rawX
                        moveY= v.y - event.rawY
                        delay(1000)
                        changeView()
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    v.animate() .x(event.rawX + moveX) .y(event.rawY + moveY) .setDuration(0) .start()
                }
            }
            true
        }

        cancelBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun changeView(){

        question?.visibility = View.INVISIBLE
        question2?.visibility = View.INVISIBLE
        img?.visibility = View.INVISIBLE
        img2?.visibility = View.INVISIBLE
        dog?.visibility = View.INVISIBLE
        dogInfo?.visibility = View.INVISIBLE

        val builder = AlertDialog.Builder(this)
        val view: View = LayoutInflater.from(this).inflate(R.layout.activity_answer2, null)
        val intent = Intent(this, MainActivity::class.java)
        dialog = builder.create()
        dialog!!.setView(view)
        dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)

        CoroutineScope(Main).launch {
            dialog!!.show()
            delay(3000)
            dialog!!.dismiss()
            startActivity(intent)
            finish()
        }

    }

}