package com.example.carry_stray_dogs

import android.R.attr.left
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.activity_flightsearch.endText
import kotlinx.android.synthetic.main.activity_flightsearch.startText
import kotlinx.android.synthetic.main.activity_searchresult.*


class SearchResultActivity : AppCompatActivity() {

    var dynamicButton : ImageButton? =null
    var circleView : ImageView? =null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
        /*
        for(i: Int in 1..10) {
            dynamicButton = Button(this)
            circleView = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                changeDP(130)
            )  //width height

            //circle crop
            Glide.with(this).load(R.drawable.dog).circleCrop().into(circleView!!)

            dynamicButton!!.layoutParams = layoutParams

            //button custom
            dynamicButton!!.background = ContextCompat.getDrawable(this, R.drawable.searchresult_btn_custom)
            dynamicButton!!.text = "PETCH !!!!!"

            dynamicButton!!.setCompoundDrawablesWithIntrinsicBounds(circleView!!.drawable, null, null, null)

            if(circleView!!.drawable==null){
                Log.i("null? ","yes")
            }
            else{
                Log.i("null? ","no")
                Log.i("null? ",circleView!!.drawable.toString())
            }

            layoutParams.setMargins(changeDP(0), changeDP(10), changeDP(0), changeDP(0)) //left top right bottom
            buttonview.addView(dynamicButton)
        }
       */
        for(i: Int in 1..10) {
            dynamicButton = ImageButton(this)
            circleView = ImageView(this)

            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                changeDP(130)
            )  //width height

            //circle crop
            Glide.with(this).load(R.drawable.dog).circleCrop().into(dynamicButton!!)

            dynamicButton!!.layoutParams = layoutParams

            //button custom
            dynamicButton!!.background = ContextCompat.getDrawable(this, R.drawable.searchresult_btn_custom)
            layoutParams.setMargins(changeDP(20), changeDP(10), changeDP(0), changeDP(20)) //left top right bottom
            layoutParams.gravity = Gravity.LEFT or Gravity.CENTER_HORIZONTAL
            buttonview.addView(dynamicButton)
        }
    }

    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }
}