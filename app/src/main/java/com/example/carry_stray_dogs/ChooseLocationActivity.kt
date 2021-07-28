package com.example.carry_stray_dogs

import android.content.Intent
import android.location.*
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chooselocation.*
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class ChooseLocationActivity : AppCompatActivity() {

    var start : String ?=null
    var end : String ?=null
    var type : String ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chooselocation)

        //액션바 제거
        var actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var intent = getIntent()
        start = intent.getStringExtra("flight_start")
        end = intent.getStringExtra("flight_end")
        type = intent.getStringExtra("flight")

        if (type.equals("start")) {
            text_message.setText("어디서 출발하시나요?")
        } else if (type.equals("end")) {
            text_message.setText("어디로 도착하시나요?")
        }

        backBtn.setOnClickListener {
            val intent3 = Intent(this, FlightSearchActivity::class.java)
            intent3.putExtra("flight_start",start)
            intent3.putExtra("flight_end",end)
            startActivity(intent3)
        }
    }

     fun onClick(v: View?){

        val b = v as Button
        val buttonText = b.text.toString()

        val intent2 = Intent(this, FlightSearchActivity::class.java)
        if(type.equals("start")){
            intent2.putExtra("flight_start",buttonText)
            intent2.putExtra("flight_end",end)
        }
        else if(type.equals("end")){
            intent2.putExtra("flight_start",start)
            intent2.putExtra("flight_end",buttonText)
        }
        startActivity(intent2)
    }

}