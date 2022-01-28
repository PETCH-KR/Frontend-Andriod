package com.example.carry_stray_dogs

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class FindPwdActivity2 : AppCompatActivity() {

    //Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl( "https://ziho-dev.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(RetrofitAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        setContentView(R.layout.activity_findpwd2)

        var getIntent :Intent = intent

        var backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this,FindPwdActivity1::class.java)
            startActivity(intent)
        }

        var certifyBtn = findViewById<ImageButton>(R.id.certifyBtn)
        var certifyText = findViewById<EditText>(R.id.certifyText)
        var certifyErr = findViewById<TextView>(R.id.certify_error_msg)

        certifyErr.visibility=View.GONE

        certifyBtn.setOnClickListener {

              if(getIntent.getStringExtra("certify_num")==certifyText.text.toString()){
                  Log.i("인증번호 확인: ","success")
                  val intent = Intent(this,FindPwdActivity3::class.java)
                  intent.putExtra("email",getIntent.getStringExtra("email"))
                  startActivity(intent)
              }
            else{
                  Log.i("인증번호 확인: ","fail")
                  certifyErr.visibility=View.VISIBLE
              }


        }

    }

}