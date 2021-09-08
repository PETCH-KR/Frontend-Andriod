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

class FindPwdActivity3 : AppCompatActivity() {

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

        setContentView(R.layout.activity_findpwd3)

        var getIntent :Intent = intent
        var backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        var pwdText = findViewById<EditText>(R.id.pwdText)
        var pwdcheckText = findViewById<EditText>(R.id.pwdcheckText)
        var pwdErr = findViewById<TextView>(R.id.pwd_error_msg)
        var pwdcheckErr = findViewById<TextView>(R.id.pwdcheck_error_msg)
        var pwdBtn = findViewById<ImageButton>(R.id.pwdBtn)
        var pwdcheckBtn = findViewById<ImageButton>(R.id.pwdcheckBtn)
        var confirmBtn = findViewById<ImageButton>(R.id.confirmBtn)


        pwdErr.visibility=View.GONE
        pwdcheckErr.visibility=View.GONE

        pwdText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwdText.transformationMethod = PasswordTransformationMethod.getInstance()
        pwdcheckText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwdcheckText.transformationMethod = PasswordTransformationMethod.getInstance()
        pwdText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwdcheckText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD


        var error = false
        confirmBtn.setOnClickListener{

            if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", pwdText.text.toString())){
                //pwd err
                pwdErr.visibility=View.VISIBLE
                pwdBtn.setImageResource(R.drawable.join_lock_error)
                error = true
            }
            else{
                pwdErr.visibility=View.GONE
                pwdBtn.setImageResource(R.drawable.join_lock)
            }
            if(pwdcheckText.text.toString()==""||pwdText.text.toString()!=pwdcheckText.text.toString()){
                //pwdcheck err
                pwdcheckErr.visibility=View.VISIBLE
                pwdcheckBtn.setImageResource(R.drawable.join_lock_error)
                error = true
            }
            else{
                pwdcheckErr.visibility=View.GONE
                pwdcheckBtn.setImageResource(R.drawable.join_lock)
            }


            if(!error){
                //서버 연동 (비밀번호 변경)
                var renew_pwd: HashMap<String, String> = HashMap()
                var email = getIntent.getStringExtra("email")
                if (email != null) {
                    renew_pwd.put("email", email)
                }
                renew_pwd.put("new_password", pwdText.text.toString())
                val intent = Intent(this, FindPwdActivity4::class.java)

                apiService.resetpwdAPI(renew_pwd)?.enqueue(object : Callback<Post?> {
                    override fun onFailure(call: Call<Post?>, t: Throwable) {
                        Log.d(ContentValues.TAG, "실패 : {${t}}")
                    }
                    override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                        if(response.body()?.success==true){
                            Log.i("비밀번호 변경: ","success")
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Log.i("비밀번호 변경: ","fail")
                            Log.i("비밀번호 변경: ",response.code().toString())
                        }
                    }
                })

            }

        }

    }

}

