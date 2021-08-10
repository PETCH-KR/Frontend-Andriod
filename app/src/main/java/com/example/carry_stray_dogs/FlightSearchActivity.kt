package com.example.carry_stray_dogs

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.content.Context

import android.content.Intent.getIntent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_flightsearch.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.w3c.dom.Text

class FlightSearchActivity : Fragment() {

    private var myContext: FragmentActivity? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v : View = inflater.inflate(R.layout.activity_flightsearch, container, false)

        //navigate hide
        val mainAct = activity as MainActivity
        mainAct.HideBottomNavigation(true)


        val start_date = v.findViewById<TextView>(R.id.start_date)
        val end_date = v.findViewById<TextView>(R.id.end_date)
        val startText = v.findViewById<TextView>(R.id.startText)
        val endText = v.findViewById<TextView>(R.id.endText)
        val swapBtn = v.findViewById<ImageButton>(R.id.swapBtn)
        val start = v.findViewById<ImageButton>(R.id.start)
        val end = v.findViewById<ImageButton>(R.id.end)
        val start_btn = v.findViewById<Button>(R.id.start_btn)
        val end_btn = v.findViewById<Button>(R.id.end_btn)
        val cancelBtn = v.findViewById<ImageButton>(R.id.cancelBtn)
        val searchBtn = v.findViewById<ImageButton>(R.id.searchBtn)


        //오늘날짜로 default 설정
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        start_date.text = formatted.split("-")[0]+"."+formatted.split("-")[1]+"."+formatted.split("-")[2]
        end_date.text = formatted.split("-")[0]+"."+formatted.split("-")[1]+"."+formatted.split("-")[2]
        val minDate = Calendar.getInstance()
        val minDate2 = Calendar.getInstance()

        //fragment 데이터 전달 받는
        var bundle: Bundle
        if(arguments!=null){
            bundle = arguments as Bundle
            startText.text=bundle.getString("flight_start")
            endText.text=bundle.getString("flight_end")
        }

        swapBtn.setOnClickListener {
            val start = startText.text.toString()
            val end = endText.text.toString()

            if(start == "출발지"){
                Toast.makeText(context, "출발지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(end == "도착지"){
                Toast.makeText(context, "도착지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                startText.text = end
                endText.text = start
            }
        }

        start.setOnClickListener{

            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            val fragment1 : Fragment = ChooseLocationActivity()
            val bundle = Bundle()
            bundle.putString("flight","start")
            bundle.putString("flight_start",startText.text.toString())
            bundle.putString("flight_end",endText.text.toString())
            fragment1.arguments=bundle
            transaction.replace(R.id.container,fragment1)
            transaction.commit()

        }

        end.setOnClickListener{

            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            val fragment1 : Fragment = ChooseLocationActivity()
            val bundle = Bundle()
            bundle.putString("flight","end")
            bundle.putString("flight_start",startText.text.toString())
            bundle.putString("flight_end",endText.text.toString())
            fragment1.arguments=bundle
            transaction.replace(R.id.container,fragment1)
            transaction.commit()

        }

        start_btn.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var listener = DatePickerDialog.OnDateSetListener{_,i,i2,i3 -> start_date.text = ""+i+"."+(i2+1)+"."+i3 }
            var picker = context?.let { it1 -> DatePickerDialog(it1,listener,year,month,day) }
            //날짜 제한 기준 : 현재 날짜
            minDate.set(Integer.parseInt(formatted.split("-")[0]),Integer.parseInt(formatted.split("-")[1])-1,Integer.parseInt(formatted.split("-")[2]))
            picker?.getDatePicker()?.setMinDate(minDate.getTime().getTime())
            picker?.show()
        }

        end_btn.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var listener = DatePickerDialog.OnDateSetListener{_,i,i2,i3 -> end_date.text = ""+i+"."+(i2+1)+"."+i3 }
            var picker = context?.let { it1 -> DatePickerDialog(it1,listener,year,month,day) }
            //날짜 제한 기준 : 시작 날짜
            minDate2.set(Integer.parseInt(start_date.text.split(".")[0]),Integer.parseInt(start_date.text.split(".")[1])-1,Integer.parseInt(start_date.text.split(".")[2]))
            picker?.getDatePicker()?.setMinDate(minDate2.getTime().getTime())
            picker?.show()
        }

        cancelBtn.setOnClickListener{
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }

        searchBtn.setOnClickListener {
            val start = startText.text.toString()
            val end = endText.text.toString()
            if (start == "출발지") {
                Toast.makeText(context, "출발지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            } else if (end == "도착지") {
                Toast.makeText(context, "도착지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            } else {

                val transaction = myContext!!.supportFragmentManager.beginTransaction()
                val fragment1 : Fragment = SearchResultActivity()
                val bundle = Bundle()
                bundle.putString("flight_start", startText.text.toString())
                bundle.putString("flight_end", endText.text.toString())
                bundle.putString("flight_start_time", start_date.text.toString())
                bundle.putString("flight_end_time", end_date.text.toString())
                fragment1.arguments=bundle
                transaction.replace(R.id.container,fragment1)
                transaction.commit()

            }
        }

        return v
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

}


