package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chooselocation.*
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.search_btn

class ChooseLocationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooselocation)

        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var intent = getIntent()
        if(intent.getStringExtra("flight").equals("start")){
            text_message.setText("어디서 출발하시나요?")
        }
        else if(intent.getStringExtra("flight").equals("end")){
            text_message.setText("어디로 도착하시나요?")
        }

        backBtn.setOnClickListener{
            val intent = Intent(this,FlightSearchActivity::class.java)
            startActivity(intent)
        }
    }
}