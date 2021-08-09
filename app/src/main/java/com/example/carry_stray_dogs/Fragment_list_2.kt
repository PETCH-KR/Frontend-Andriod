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