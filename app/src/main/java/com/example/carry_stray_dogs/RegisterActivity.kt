package com.example.carry_stray_dogs

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_chooselocation.*
import kotlinx.android.synthetic.main.activity_flightsearch.endText
import kotlinx.android.synthetic.main.activity_flightsearch.startText
import kotlinx.android.synthetic.main.activity_searchresult.*
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class RegisterActivity : Fragment() {
    private var myContext: FragmentActivity? = null
    var dog_name: String? = null
    var dog_care: String? = null

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.activity_register, container, false)

        //navigate hide
        val mainAct = activity as MainActivity
        mainAct.HideBottomNavigation(true)

        //fragment 데이터 전달 받는
        var bundle: Bundle = arguments as Bundle
        dog_name = bundle.getString("dog_name").toString()
        dog_care = bundle.getString("dog_care").toString()

        Log.i("DogInfo : ","dog name: $dog_name , dog_care: $dog_care")

        val backBtn : ImageButton = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener{

            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            val fragment1 : Fragment = AdoptInfoActivity()
            val bundle2 = Bundle()
            fragment1.arguments=bundle2
            transaction.replace(R.id.container,fragment1)
            transaction.commit()

        }

        val dogPic : ImageView = view.findViewById(R.id.dogPic)
        Glide.with(this).load(R.drawable.dog).circleCrop().into(dogPic!!)


        //trip schedule
        val start_date = view.findViewById<TextView>(R.id.startDate)
        val end_date = view.findViewById<TextView>(R.id.endDate)
        val startText = view.findViewById<TextView>(R.id.startText)
        val endText = view.findViewById<TextView>(R.id.endText)

        //나라
        startText.text = "출발지"
        endText.text = "도착지"
        //날짜
        start_date.text = "2021.01.01"
        end_date.text ="2021.01.01"

        //check
        val dog_check = view.findViewById<ImageButton>(R.id.dog_check)
        dog_check.id = 0
        val dog_check2 = view.findViewById<ImageButton>(R.id.dog_check2)
        dog_check2.id = 0
        val dog_check3 = view.findViewById<ImageButton>(R.id.dog_check3)
        dog_check3.id = 0
        val dog_check4 = view.findViewById<ImageButton>(R.id.dog_check4)
        dog_check4.id = 0

        dog_check.setOnClickListener{
            if(dog_check.id==0){
                dog_check.setImageResource(R.drawable.info_checked)
                dog_check.id = 1
            }
            else{
                dog_check.setImageResource(R.drawable.info_unchecked)
                dog_check.id = 0
            }
        }
        dog_check2.setOnClickListener{
            if(dog_check2.id==0){
                dog_check2.setImageResource(R.drawable.info_checked)
                dog_check2.id = 1
            }
            else{
                dog_check2.setImageResource(R.drawable.info_unchecked)
                dog_check2.id = 0
            }
        }
        dog_check3.setOnClickListener{
            if(dog_check3.id==0){
                dog_check3.setImageResource(R.drawable.info_checked)
                dog_check3.id = 1
            }
            else{
                dog_check3.setImageResource(R.drawable.info_unchecked)
                dog_check3.id = 0
            }
        }
        dog_check4.setOnClickListener{
            if(dog_check4.id==0){
                dog_check4.setImageResource(R.drawable.info_checked)
                dog_check4.id = 1
            }
            else{
                dog_check4.setImageResource(R.drawable.info_unchecked)
                dog_check4.id = 0
            }
        }

        val list1_check = view.findViewById<ImageButton>(R.id.list1_check)
        list1_check.id = 0
        val list2_check = view.findViewById<ImageButton>(R.id.list2_check)
        list2_check.id = 0
        val list3_check = view.findViewById<ImageButton>(R.id.list3_check)
        list3_check.id = 0

        list1_check.setOnClickListener{
            if(list1_check.id==0){
                list1_check.setImageResource(R.drawable.info_checked)
                list1_check.id = 1
            }
            else{
                list1_check.setImageResource(R.drawable.info_unchecked)
                list1_check.id = 0
            }
        }
        list2_check.setOnClickListener{
            if(list2_check.id==0){
                list2_check.setImageResource(R.drawable.info_checked)
                list2_check.id = 1
            }
            else{
                list2_check.setImageResource(R.drawable.info_unchecked)
                list2_check.id = 0
            }
        }
        list3_check.setOnClickListener{
            if(list3_check.id==0){
                list3_check.setImageResource(R.drawable.info_checked)
                list3_check.id = 1
            }
            else{
                list3_check.setImageResource(R.drawable.info_unchecked)
                list3_check.id = 0
            }
        }


        return view
    }


    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

}