package com.example.carry_stray_dogs

import android.app.Activity
import android.graphics.drawable.Drawable
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
import com.bumptech.glide.request.target.Target


class CompanyInfo_DogInfo_Fragment  : Fragment(){
    private var myContext: FragmentActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view :View = inflater.inflate(R.layout.activity_companyinfo_doginfo, container, false)

        var dog_pic : ImageView = view.findViewById(R.id.dogPic)
        Glide.with(this).load(R.drawable.dog).circleCrop().into(dog_pic!!)
        var dog_info : TextView = view.findViewById(R.id.dogInfo)
        dog_info.text = "품종 : 믹스견 \n보호기관 : 00보호기관\n출국 가능 마감일 : 2021-01--01\n등록일 : 2021-01-01"

        val registerBtn : ImageButton = view.findViewById(R.id.registerBtn)
        registerBtn.setOnClickListener{

            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            val fragment1 : Fragment = RegisterActivity()
            val bundle2 = Bundle()
            bundle2.putString("dog_name","믹스견")
            bundle2.putString("care_care","00보호기관")
            fragment1.arguments=bundle2
            transaction.replace(R.id.container,fragment1)
            transaction.commit()

        }

        return view
    }

    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

    fun newInstant() : CompanyInfo_DogInfo_Fragment
    {
        val args = Bundle()
        val frag = CompanyInfo_DogInfo_Fragment()
        frag.arguments = args
        return frag
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }
}