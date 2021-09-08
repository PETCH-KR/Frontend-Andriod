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

class FindPwdActivity1 : AppCompatActivity() {

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

        setContentView(R.layout.activity_findpwd1)


        var backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        var certifyBtn = findViewById<ImageButton>(R.id.certifyBtn)
        var emailText = findViewById<EditText>(R.id.emailText)
        var emailErr = findViewById<TextView>(R.id.email_error_msg)

        emailErr.visibility=View.GONE

        certifyBtn.setOnClickListener {

            var pattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
            if(emailText.text.toString()=="" || !pattern.matcher(emailText.text.toString()).matches()){
                //email err
                emailErr.text = "이메일을 정확하게 입력해주세요."
                emailErr.visibility=View.VISIBLE
            }
            else{
                emailErr.visibility=View.GONE

                //서버 연동 (인증전송 확인)
                var find_pwd: HashMap<String, String> = HashMap()
                find_pwd.put("email", emailText.text.toString())


                val intent = Intent(this, FindPwdActivity2::class.java)

                apiService.findpwdAPI(find_pwd)?.enqueue(object : Callback<Post?> {
                    override fun onFailure(call: Call<Post?>, t: Throwable) {
                        Log.d(ContentValues.TAG, "실패 : {${t}}")
                    }
                    override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                        if(response.body()?.success==true){
                            Log.i("인증번호 전송: ","success")
                            response.body()!!.getDataList()?.verified_number?.let { it1 ->
                                Log.i("인증번호: ",
                                    it1
                                )
                            }
                            intent.putExtra("email",emailText.text.toString())
                            intent.putExtra("certify_num",
                                response.body()!!.getDataList()?.verified_number
                            )
                            startActivity(intent)
                        }
                        else{
                            Log.i("인증번호 전송 : ","fail")
                        }
                    }
                })


            }

        }

    }

}