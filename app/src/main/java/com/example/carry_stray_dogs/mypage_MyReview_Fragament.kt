package com.example.carry_stray_dogs

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class mypage_MyReview_Fragament  : Fragment(){

    var dynamicInfo : TextView? =null
    var group : LinearLayout? =null
    var group2 : RelativeLayout? =null
    var circleView : ImageView? =null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view :View = inflater.inflate(R.layout.fragment_mypage_myreview, container, false)

        //My Review Info
        val buttonview = view.findViewById<LinearLayout>(R.id.Info)
        buttonview.isClickable=true

        for(i: Int in 1..10) {

            //둥근 사각형
            circleView = ImageView(this.context)
            Glide.with(this).load(R.drawable.home_temp_after).apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(20))).into(circleView!!)
            val circle_params = LinearLayout.LayoutParams(changeDP(100), changeDP(85))
            circle_params.setMargins(changeDP(10), changeDP(5), changeDP(20), changeDP(5))
            circleView!!.layoutParams = circle_params

            //group : circle crop image + info text
            dynamicInfo = TextView(this.context)
            dynamicInfo!!.textSize = 13f
            dynamicInfo!!.text = " 일시 : 2021-01-01 \n 보호기관 : 00보호기관 \n 이동봉사 나라 : 캐나다"
            dynamicInfo!!.gravity = Gravity.CENTER_VERTICAL
            dynamicInfo!!.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, changeDP(80))

            group = LinearLayout(this.context)
            group!!.isClickable=true
            //group!!.isDuplicateParentStateEnabled=true
            group!!.orientation = LinearLayout.HORIZONTAL
            val group_params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            group_params.setMargins(changeDP(0), changeDP(5), changeDP(20), changeDP(15))
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

    fun newInstant() : mypage_MyReview_Fragament
    {
        val args = Bundle()
        val frag = mypage_MyReview_Fragament()
        frag.arguments = args
        return frag
    }


}