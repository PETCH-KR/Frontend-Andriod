package com.example.carry_stray_dogs

import android.app.Activity
import android.content.Intent
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_chooselocation.*
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class ChooseLocationActivity : Fragment() ,View.OnClickListener {

    private var myContext: FragmentActivity? = null

    var start: String? = null
    var end: String? = null
    var type: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.activity_chooselocation, container, false)

        //navigate hide
        val mainAct = activity as MainActivity
        mainAct.HideBottomNavigation(true)

        val text_message : TextView = view.findViewById(R.id.text_message)
        //fragment 데이터 전달 받는
        var bundle: Bundle
        if(arguments!=null){
            bundle = arguments as Bundle

            start = bundle.getString("flight_start")
            end = bundle.getString("flight_end")
            type = bundle.getString("flight")
            if (type.equals("start")) {
                text_message.setText("어디서 출발하시나요?")
            } else if (type.equals("end")) {
                text_message.setText("어디로 도착하시나요?")
            }
        }

        val backBtn : ImageButton = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {

            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            val fragment1 : Fragment = FlightSearchActivity()

            val bundle = Bundle()
            bundle.putString("flight_start",start)
            bundle.putString("flight_end",end)
            fragment1.arguments=bundle
            transaction.replace(R.id.container,fragment1)
            transaction.commit()
        }


        val GMP : Button = view.findViewById(R.id.GMP)
        GMP.setOnClickListener(this)
        val INC : Button = view.findViewById(R.id.INC)
        INC.setOnClickListener(this)
        val CJU : Button = view.findViewById(R.id.CJU)
        CJU.setOnClickListener(this)
        val LAX : Button = view.findViewById(R.id.LAX)
        LAX.setOnClickListener(this)
        val SFO : Button = view.findViewById(R.id.SFO)
        SFO.setOnClickListener(this)
        val JFK : Button = view.findViewById(R.id.JFK)
        JFK.setOnClickListener(this)
        val EWR : Button = view.findViewById(R.id.EWR)
        EWR.setOnClickListener(this)
        val LGA : Button = view.findViewById(R.id.LGA)
        LGA.setOnClickListener(this)
        val SWF : Button = view.findViewById(R.id.SWF)
        SWF.setOnClickListener(this)
        val HNL : Button = view.findViewById(R.id.HNL)
        HNL.setOnClickListener(this)
        val BOS : Button = view.findViewById(R.id.BOS)
        BOS.setOnClickListener(this)
        val YYZ : Button = view.findViewById(R.id.YYZ)
        YYZ.setOnClickListener(this)
        val YKF : Button = view.findViewById(R.id.YKF)
        YKF.setOnClickListener(this)
        val YHM : Button = view.findViewById(R.id.YHM)
        YHM.setOnClickListener(this)
        val YVR : Button = view.findViewById(R.id.YVR)
        YVR.setOnClickListener(this)
        val YXX : Button = view.findViewById(R.id.YXX)
        YXX.setOnClickListener(this)
        val CXH : Button = view.findViewById(R.id.CXH)
        CXH.setOnClickListener(this)

        return view
    }


    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    override fun onClick(v: View?) {
        val b = v as Button
        val buttonText = b.text.toString()

        val transaction2 = myContext!!.supportFragmentManager.beginTransaction()
        val fragment2 : Fragment= FlightSearchActivity()

        val bundle2 = Bundle()
        if(type.equals("start")){
            bundle2.putString("flight_start",buttonText)
            bundle2.putString("flight_end",end)
        }
        else if(type.equals("end")){
            bundle2.putString("flight_start",start)
            bundle2.putString("flight_end",buttonText)
        }
        fragment2.arguments=bundle2
        transaction2.replace(R.id.container,fragment2)
        transaction2.commit()
    }



}