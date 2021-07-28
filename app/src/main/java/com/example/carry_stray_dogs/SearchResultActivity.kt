package com.example.carry_stray_dogs

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.activity_flightsearch.endText
import kotlinx.android.synthetic.main.activity_flightsearch.startText
import kotlinx.android.synthetic.main.activity_searchresult.*


class SearchResultActivity : AppCompatActivity() {

    var tagButton : Button? =null
    var dynamicButton : Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchresult)

        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var intent = getIntent()
        startText.text = intent.getStringExtra("flight_start").toString()
        endText.text = intent.getStringExtra("flight_end").toString()
        startDate.text = intent.getStringExtra("flight_start_time").toString()
        endDate.text = intent.getStringExtra("flight_end_time").toString()
        //서버검색

        backBtn.setOnClickListener{
            val intent = Intent(this,FlightSearchActivity::class.java)
            intent.putExtra("flight_start",startText.text.toString())
            intent.putExtra("flight_end",endText.text.toString())
            startActivity(intent)
        }

        iconbtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        /*
        //Tag
        val tagView = findViewById<LinearLayout>(R.id.tagView)
        for(i: Int in 1..10){
            tagButton = Button(this)
            val layoutParams2 = LinearLayout.LayoutParams(changeDP(80),LinearLayout.LayoutParams.MATCH_PARENT)  //width height
            tagButton!!.layoutParams = layoutParams2
            tagButton!!.text = "#태그필터"
            tagButton!!.textSize = 12F;

            tagButton!!.background = ContextCompat.getDrawable(this, R.drawable.searchresult_btn_custom)
            tagButton!!.setBackgroundColor(Color.WHITE)

            layoutParams2.setMargins(changeDP(0), changeDP(1), changeDP(10), 1) //left top right bottom
            tagView.addView(tagButton)

        }
        */


        //Adapt Dog Info
        val buttonview = findViewById<LinearLayout>(R.id.Info)
        for(i: Int in 1..10) {
            dynamicButton = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                changeDP(130)
            )  //width height
            dynamicButton!!.layoutParams = layoutParams
            layoutParams.setMargins(
                changeDP(0),
                changeDP(0),
                changeDP(0),
                0
            ) //left top right bottom
            buttonview.addView(dynamicButton)
        }
    }

    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }
}