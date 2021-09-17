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

class Fragment_list_2 : Fragment(){

    private var myContext: FragmentActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view :View = inflater.inflate(R.layout.fragment_list2, container, false)
        var dog_pic : ImageView = view.findViewById(R.id.dog_Pic)
        Glide.with(this).load(R.drawable.dog).circleCrop().into(dog_pic!!)
        var dog_Info : TextView = view.findViewById(R.id.dog_Info)
        dog_Info.text = "품종 : 믹스견\n보호기관 : 00보호기관\n출국 가능 마감일 : 2021-01-01\n등록일 : 2021-01-01"


        var backBtn : ImageButton = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener{
            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,Fragment_list())
            transaction.commit()
        }

        var dot1 : ImageButton = view.findViewById(R.id.dot1)
        var dot2 : ImageButton = view.findViewById(R.id.dot2)
        var dot3 : ImageButton = view.findViewById(R.id.dot3)
        var dot4 : ImageButton = view.findViewById(R.id.dot4)
        var image_dot1 : ImageView = view.findViewById(R.id.image_dot1)
        var image_dot2 : ImageView = view.findViewById(R.id.image_dot2)
        var image_dot3 : ImageView = view.findViewById(R.id.image_dot3)
        var image_dot4 : ImageView = view.findViewById(R.id.image_dot4)
        image_dot2.visibility=View.INVISIBLE
        image_dot3.visibility=View.INVISIBLE
        image_dot4.visibility=View.INVISIBLE

        dot1.setOnClickListener{
            dot1.setImageResource(R.drawable.list_bluedot)
            dot2.setImageResource(R.drawable.list_whitedot)
            dot3.setImageResource(R.drawable.list_whitedot)
            dot4.setImageResource(R.drawable.list_whitedot)

            image_dot1.visibility=View.VISIBLE
            image_dot2.visibility=View.INVISIBLE
            image_dot3.visibility=View.INVISIBLE
            image_dot4.visibility=View.INVISIBLE

        }
        dot2.setOnClickListener{
            dot2.setImageResource(R.drawable.list_bluedot)
            dot1.setImageResource(R.drawable.list_whitedot)
            dot3.setImageResource(R.drawable.list_whitedot)
            dot4.setImageResource(R.drawable.list_whitedot)

            image_dot2.visibility=View.VISIBLE
            image_dot1.visibility=View.INVISIBLE
            image_dot3.visibility=View.INVISIBLE
            image_dot4.visibility=View.INVISIBLE
        }
        dot3.setOnClickListener{
            dot3.setImageResource(R.drawable.list_bluedot)
            dot2.setImageResource(R.drawable.list_whitedot)
            dot1.setImageResource(R.drawable.list_whitedot)
            dot4.setImageResource(R.drawable.list_whitedot)

            image_dot3.visibility=View.VISIBLE
            image_dot2.visibility=View.INVISIBLE
            image_dot1.visibility=View.INVISIBLE
            image_dot4.visibility=View.INVISIBLE
        }
        dot4.setOnClickListener{
            dot4.setImageResource(R.drawable.list_bluedot)
            dot2.setImageResource(R.drawable.list_whitedot)
            dot3.setImageResource(R.drawable.list_whitedot)
            dot1.setImageResource(R.drawable.list_whitedot)

            image_dot4.visibility=View.VISIBLE
            image_dot2.visibility=View.INVISIBLE
            image_dot3.visibility=View.INVISIBLE
            image_dot1.visibility=View.INVISIBLE
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