package com.example.carry_stray_dogs

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_flightsearch.endText
import kotlinx.android.synthetic.main.activity_flightsearch.startText
import kotlinx.android.synthetic.main.activity_searchresult.*


class SearchResultActivity : AppCompatActivity() {

    var dynamicInfo : TextView? =null
    var group : LinearLayout? =null
    var group2 : RelativeLayout? =null
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

        var tagBtn1 : Button = findViewById(R.id.tagBtn1)
        var tagBtn2 : Button = findViewById(R.id.tagBtn2)
        var tagBtn3 : Button = findViewById(R.id.tagBtn3)
        var tagBtn4 : Button = findViewById(R.id.tagBtn4)
        var tagBtn5 : Button = findViewById(R.id.tagBtn5)
        var tagBtn6 : Button = findViewById(R.id.tagBtn6)
        var tagBtn7 : Button = findViewById(R.id.tagBtn7)
        var tagBtn8 : Button = findViewById(R.id.tagBtn8)
        var tagBtn9 : Button = findViewById(R.id.tagBtn9)
        var tagBtn10 : Button = findViewById(R.id.tagBtn10)

        tagBtn1.setOnClickListener{
            if(tagBtn1.textColors.equals(Color.WHITE)){
                tagBtn1.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn1.setTextColor(Color.BLACK)
            }
            else{
                tagBtn1.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn1.setTextColor(Color.WHITE)
            }
        }
        tagBtn2.setOnClickListener{
            if(tagBtn2.textColors.equals(Color.WHITE)){
                tagBtn2.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn2.setTextColor(Color.BLACK)
            }
            else{
                tagBtn2.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn2.setTextColor(Color.WHITE)
            }
        }
        tagBtn3.setOnClickListener{
            if(tagBtn3.textColors.equals(Color.WHITE)){
                tagBtn3.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn3.setTextColor(Color.BLACK)
            }
            else{
                tagBtn3.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn3.setTextColor(Color.WHITE)
            }
        }
        tagBtn4.setOnClickListener{
            if(tagBtn4.textColors.equals(Color.WHITE)){
                tagBtn4.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn4.setTextColor(Color.BLACK)
            }
            else{
                tagBtn4.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn4.setTextColor(Color.WHITE)
            }
        }
        tagBtn5.setOnClickListener{
            if(tagBtn5.textColors.equals(Color.WHITE)){
                tagBtn5.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn5.setTextColor(Color.BLACK)
            }
            else{
                tagBtn5.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn5.setTextColor(Color.WHITE)
            }
        }
        tagBtn6.setOnClickListener{
            if(tagBtn6.textColors.equals(Color.WHITE)){
                tagBtn6.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn6.setTextColor(Color.BLACK)
            }
            else{
                tagBtn6.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn6.setTextColor(Color.WHITE)
            }
        }
        tagBtn7.setOnClickListener{
            if(tagBtn7.textColors.equals(Color.WHITE)){
                tagBtn7.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn7.setTextColor(Color.BLACK)
            }
            else{
                tagBtn7.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn7.setTextColor(Color.WHITE)
            }
        }
        tagBtn8.setOnClickListener{
            if(tagBtn8.textColors.equals(Color.WHITE)){
                tagBtn8.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn8.setTextColor(Color.BLACK)
            }
            else{
                tagBtn8.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn8.setTextColor(Color.WHITE)
            }
        }
        tagBtn9.setOnClickListener{
            if(tagBtn9.textColors.equals(Color.WHITE)){
                tagBtn9.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn9.setTextColor(Color.BLACK)
            }
            else{
                tagBtn9.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn9.setTextColor(Color.WHITE)
            }
        }
        tagBtn10.setOnClickListener{
            if(tagBtn10.textColors.equals(Color.WHITE)){
                tagBtn10.setBackgroundResource(R.drawable.searchresult_btn_custom)
                tagBtn10.setTextColor(Color.BLACK)
            }
            else{
                tagBtn10.setBackgroundResource(R.drawable.searchresult_btn_custom_tag)
                tagBtn10.setTextColor(Color.WHITE)
            }
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
        buttonview.isClickable=true

        for(i: Int in 1..10) {

            //circle crop
            circleView = ImageView(this)
            Glide.with(this).load(R.drawable.dog).circleCrop().into(circleView!!)
            circleView!!.layoutParams = LinearLayout.LayoutParams(changeDP(130), changeDP(80))

            //group : circle crop image + info text
            dynamicInfo = TextView(this)
            dynamicInfo!!.textSize = 13f
            dynamicInfo!!.text = " 품종 : 믹스견 \n 보호기관 : 00보호기관 \n 출국 가능 마감일 : 2021-01-01 \n 등록일 : 2021-01-01"
            dynamicInfo!!.gravity = Gravity.CENTER_VERTICAL
            dynamicInfo!!.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, changeDP(80))

            group = LinearLayout(this)
            group!!.isClickable=true
            //group!!.isDuplicateParentStateEnabled=true
            group!!.orientation = LinearLayout.HORIZONTAL
            val group_params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, changeDP(110))
            group_params.setMargins(changeDP(5), changeDP(0), changeDP(5), changeDP(14))
            group!!.layoutParams = group_params
            group!!.setBackgroundResource(R.drawable.searchresult_btn_custom)
            group!!.gravity = Gravity.CENTER
            circleView!!.isClickable = false
            dynamicInfo!!.isClickable = false
            group!!.addView(circleView)
            group!!.addView(dynamicInfo)

            group!!.setOnClickListener {
                Log.i("click layout","$i 번째 layout")
                //button event
            }

            buttonview.addView(group)
        }
    }


    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }
}