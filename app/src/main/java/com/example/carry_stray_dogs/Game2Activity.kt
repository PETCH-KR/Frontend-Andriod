package com.example.carry_stray_dogs

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
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

class Game2Activity : AppCompatActivity(){

    var dialog : AlertDialog ?=null
    var moveX = 0f
    var moveY = 0f

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game2)
        //액션바 제거
        var actionBar : ActionBar? = supportActionBar
        actionBar?.hide()

        var cancelBtn = findViewById<ImageButton>(R.id.cancelBtn)
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
        val intent = Intent(this, Game3Activity::class.java)
        startActivity(intent)
        finish()
    }

}