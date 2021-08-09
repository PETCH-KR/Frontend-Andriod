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


class ChooseLocationActivity : Fragment() {

    private var myContext: FragmentActivity? = null

    var start: String? = null
    var end: String? = null
    var type: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.activity_chooselocation, container, false)

        /*
        start = intent.getStringExtra("flight_start")
        end = intent.getStringExtra("flight_end")
        type = intent.getStringExtra("flight")

        if (type.equals("start")) {
            text_message.setText("어디서 출발하시나요?")
        } else if (type.equals("end")) {
            text_message.setText("어디로 도착하시나요?")
        }

        backBtn.setOnClickListener {
            val intent3 = Intent(this, FlightSearchActivity::class.java)
            intent3.putExtra("flight_start",start)
            intent3.putExtra("flight_end",end)
            startActivity(intent3)
        }
        */

        val b = view as Button
        val buttonText = b.text.toString()

        if(type.equals("start")){
            //intent2.putExtra("flight_start",buttonText)
            //intent2.putExtra("flight_end",end)
        }
        else if(type.equals("end")){
            //intent2.putExtra("flight_start",start)
            //intent2.putExtra("flight_end",buttonText)
        }
        val transaction = myContext!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FlightSearchActivity())
        transaction.commit()

        return view
    }


    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }


}