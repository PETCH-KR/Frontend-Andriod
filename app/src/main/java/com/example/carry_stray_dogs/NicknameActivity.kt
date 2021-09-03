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

class NicknameActivity : AppCompatActivity() {

    //Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl( "https://ziho-dev.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(RetrofitAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent : Intent = intent

        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        setContentView(R.layout.activity_nickname)

        var backBtn = findViewById<ImageButton>(R.id.cancelBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }

        var joinBtn = findViewById<ImageButton>(R.id.joinBtn)
        var nickText = findViewById<EditText>(R.id.nickText)
        var deleteBtn = findViewById<ImageButton>(R.id.deleteButton)
        var nickErr = findViewById<TextView>(R.id.nick_error_msg)

        nickErr.visibility=View.GONE
        deleteBtn.setImageResource(R.drawable.game_cancel)
        deleteBtn.setOnClickListener{
            nickText.setText("")
        }

        joinBtn.setOnClickListener {

            //서버 연동 (회원가입)
            var signup: HashMap<String, String> = HashMap()
            signup.put("name", nickText.text.toString())
            signup.put("email", intent.getStringExtra("email").toString())
            signup.put("password",intent.getStringExtra("password").toString())

            val intent2 = Intent(this, LoginActivity::class.java)

            apiService.singupAPI(signup)?.enqueue(object : Callback<Post?> {
                override fun onFailure(call: Call<Post?>, t: Throwable) {
                    Log.d(ContentValues.TAG, "실패 : {${t}}")
                }
                override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                    if(response.body()?.success==true){
                        Log.i("회원가입: ","success")
                        startActivity(intent2)
                    }
                    else{
                        Log.i("회원가입 : ","fail")
                        Log.i("회원가입 : ",response.code().toString())
                        if(response.code()==400){
                            nickErr.visibility=View.VISIBLE
                            deleteBtn.setImageResource(R.drawable.cancel_blue)
                        }
                    }
                }
            })

        }

    }

}