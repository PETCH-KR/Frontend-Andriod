package com.example.carry_stray_dogs

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
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
        minDate.set(Integer.parseInt(formatted.split("-")[0]),Integer.parseInt(formatted.split("-")[1]),Integer.parseInt(formatted.split("-")[2]))

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
            startActivity(intent)
        }
        end.setOnClickListener{
            val intent = Intent(this,ChooseLocationActivity::class.java)
            intent.putExtra("flight","end")
            startActivity(intent)
        }

        start_btn.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener{_,i,i2,i3 -> start_date.text = ""+i+"."+i2+"."+i3 }
            var picker = DatePickerDialog(this,listener,year,month,day)
            picker.getDatePicker().setMinDate(minDate.getTime().getTime())
            picker.show()
        }

        end_btn.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var listener = DatePickerDialog.OnDateSetListener{_,i,i2,i3 -> end_date.text = ""+i+"."+i2+"."+i3 }
            var picker = DatePickerDialog(this,listener,year,month,day)
            minDate.set(Integer.parseInt(start_date.text.split(".")[0]),Integer.parseInt(start_date.text.split(".")[1]),Integer.parseInt(start_date.text.split(".")[2]))
            picker.getDatePicker().setMinDate(minDate.getTime().getTime())
            picker.show()
        }

        cancelBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}