package com.example.carry_stray_dogs

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class Fragment_list : Fragment(){

    var dynamicInfo : TextView? =null
    var group : LinearLayout? =null
    var group2 : RelativeLayout? =null
    var circleView : ImageView? =null
    private var myContext: FragmentActivity? = null
    var list_detail : ImageButton ?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view :View = inflater.inflate(R.layout.fragment_list, container, false)

        //fragment 교체
        list_detail = view.findViewById(R.id.list_detail)
        list_detail!!.setOnClickListener{
            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,Fragment_list_2())
            transaction.commit()
        }

        //Company Review Info
        val buttonview = view.findViewById<LinearLayout>(R.id.Info)
        buttonview.isClickable=true

        for(i: Int in 1..4) {

            //circle crop
            circleView = ImageView(this.context)
            Glide.with(this).load(R.drawable.dog).circleCrop().into(circleView!!)
            circleView!!.layoutParams = LinearLayout.LayoutParams(changeDP(130), changeDP(80))

            //group : circle crop image + info text
            dynamicInfo = TextView(this.context)
            dynamicInfo!!.textSize = 13f
            dynamicInfo!!.text = " 품종 : 믹스견 \n 보호기관 : 00보호기관 \n 출국 가능 마감일 : 2021-01-01 \n 등록일 : 2021-01-01"
            dynamicInfo!!.gravity = Gravity.CENTER_VERTICAL
            dynamicInfo!!.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, changeDP(80))

            group = LinearLayout(this.context)
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

        return view
    }

    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }
}