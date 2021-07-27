package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.search_btn

class FlightSearchActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flightsearch)
        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        swapBtn.setOnClickListener {
           val start = startText.text.toString()
            val end = endText.text.toString()

            if(start.equals("출발지")){
                Toast.makeText(applicationContext, "출발지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(end.equals("도착지")){
                Toast.makeText(applicationContext, "도착지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                startText.text = end
                endText.text = start
            }
        }

        start.setOnClickListener{
            val intent = Intent(this,ChooseLocationActivity::class.java)
            intent.putExtra("flight","start")
            startActivity(intent)
        }
        end.setOnClickListener{
            val intent = Intent(this,ChooseLocationActivity::class.java)
            intent.putExtra("flight","end")
            startActivity(intent)
        }

        cancelBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}