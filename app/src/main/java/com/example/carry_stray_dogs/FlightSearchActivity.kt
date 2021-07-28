package com.example.carry_stray_dogs

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
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


class FlightSearchActivity : AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_flightsearch)
        //액션바 제거
        var actionBar : ActionBar? = supportActionBar
        actionBar?.hide()


        //오늘날짜로 default 설정
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        start_date.text = formatted.split("-")[0]+"."+formatted.split("-")[1]+"."+formatted.split("-")[2]
        end_date.text = formatted.split("-")[0]+"."+formatted.split("-")[1]+"."+formatted.split("-")[2]
        val minDate = Calendar.getInstance()
        val minDate2 = Calendar.getInstance()

        var intent = getIntent()
        startText.text = intent.getStringExtra("flight_start").toString()
        endText.text = intent.getStringExtra("flight_end").toString()

        swapBtn.setOnClickListener {
           val start = startText.text.toString()
            val end = endText.text.toString()

            if(start == "출발지"){
                Toast.makeText(applicationContext, "출발지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(end == "도착지"){
                Toast.makeText(applicationContext, "도착지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                startText.text = end
                endText.text = start
            }
        }

        start.setOnClickListener{
            val intent = Intent(this,ChooseLocationActivity::class.java)
            intent.putExtra("flight","start")
            intent.putExtra("flight_start",startText.text.toString())
            intent.putExtra("flight_end",endText.text.toString())
            startActivity(intent)
        }
        end.setOnClickListener{
            val intent = Intent(this,ChooseLocationActivity::class.java)
            intent.putExtra("flight","end")
            intent.putExtra("flight_start",startText.text.toString())
            intent.putExtra("flight_end",endText.text.toString())
            startActivity(intent)
        }

        start_btn.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var listener = DatePickerDialog.OnDateSetListener{_,i,i2,i3 -> start_date.text = ""+i+"."+(i2+1)+"."+i3 }
            var picker = DatePickerDialog(this,listener,year,month,day)
            //날짜 제한 기준 : 현재 날짜
            minDate.set(Integer.parseInt(formatted.split("-")[0]),Integer.parseInt(formatted.split("-")[1])-1,Integer.parseInt(formatted.split("-")[2]))
            picker.getDatePicker().setMinDate(minDate.getTime().getTime())
            picker.show()
        }

        end_btn.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var listener = DatePickerDialog.OnDateSetListener{_,i,i2,i3 -> end_date.text = ""+i+"."+(i2+1)+"."+i3 }
            var picker = DatePickerDialog(this,listener,year,month,day)
            //날짜 제한 기준 : 시작 날짜
            minDate2.set(Integer.parseInt(start_date.text.split(".")[0]),Integer.parseInt(start_date.text.split(".")[1])-1,Integer.parseInt(start_date.text.split(".")[2]))
            picker.getDatePicker().setMinDate(minDate2.getTime().getTime())
            picker.show()
        }

        cancelBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        searchBtn.setOnClickListener{
            val start = startText.text.toString()
            val end = endText.text.toString()
            if(start == "출발지"){
                Toast.makeText(applicationContext, "출발지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(end == "도착지"){
                Toast.makeText(applicationContext, "도착지를 설정해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,SearchResultActivity::class.java)
                intent.putExtra("flight_start",startText.text.toString())
                intent.putExtra("flight_end",endText.text.toString())
                intent.putExtra("flight_start_time",start_date.text.toString())
                intent.putExtra("flight_end_time",end_date.text.toString())
                startActivity(intent)
            }
        }

    }

}