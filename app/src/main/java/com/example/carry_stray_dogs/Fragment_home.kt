package com.example.carry_stray_dogs
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*

class Fragment_home : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v : View = inflater.inflate(R.layout.fragment_home, container, false)
        val btn1 : Button = v.findViewById(R.id.imgBtn1)
        val btn2 : Button = v.findViewById(R.id.imgBtn2)
        val btn3 : Button = v.findViewById(R.id.imgBtn3)
        val btn4 : Button = v.findViewById(R.id.imgBtn4)
        val btn5 : Button = v.findViewById(R.id.imgBtn5)

        btn1.setBackgroundResource(R.drawable.home_temp_animal)
        btn2.setBackgroundResource(R.drawable.home_temp_animal)
        btn3.setBackgroundResource(R.drawable.home_temp_animal)
        btn4.setBackgroundResource(R.drawable.home_temp_animal)
        btn5.setBackgroundResource(R.drawable.home_temp_animal)

        val btn6 : Button = v.findViewById(R.id.imgBtn6)
        val btn7 : Button = v.findViewById(R.id.imgBtn7)
        val btn8 : Button = v.findViewById(R.id.imgBtn8)
        val btn9 : Button = v.findViewById(R.id.imgBtn9)
        val btn10 : Button = v.findViewById(R.id.imgBtn10)

        btn6.setBackgroundResource(R.drawable.home_temp_after)
        btn7.setBackgroundResource(R.drawable.home_temp_after)
        btn8.setBackgroundResource(R.drawable.home_temp_after)
        btn9.setBackgroundResource(R.drawable.home_temp_after)
        btn10.setBackgroundResource(R.drawable.home_temp_after)

        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        search_btn.setOnClickListener {
            activity?.let{
                val intent = Intent(context, FlightSearchActivity::class.java)
                intent.putExtra("flight_start","출발지")
                intent.putExtra("flight_end","도착지")
                startActivity(intent)
            }
        }

        game.setOnClickListener {
            activity?.let{
                val intent = Intent(context, Game1Activity::class.java)
                startActivity(intent)
            }
        }

    }
    fun newInstant() : mypage_CompanyReview_Fragament
    {
        val args = Bundle()
        val frag = mypage_CompanyReview_Fragament()
        frag.arguments = args
        return frag
    }


}

