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

class JoinActivity : AppCompatActivity() {

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

        setContentView(R.layout.activity_join)

        var backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        var joinBtn = findViewById<ImageButton>(R.id.nextBtn)
        var emailText = findViewById<EditText>(R.id.emailText)
        var pwdText = findViewById<EditText>(R.id.pwdText)
        var pwdcheckText = findViewById<EditText>(R.id.pwdcheckText)
        var emailErr = findViewById<TextView>(R.id.email_error_msg)
        var pwdErr = findViewById<TextView>(R.id.pwd_error_msg)
        var pwdcheckErr = findViewById<TextView>(R.id.pwdcheck_error_msg)
        var pwdBtn = findViewById<ImageButton>(R.id.pwdBtn)
        var pwdcheckBtn = findViewById<ImageButton>(R.id.pwdcheckBtn)

        emailErr.visibility=View.GONE
        pwdErr.visibility=View.GONE
        pwdcheckErr.visibility=View.GONE

        pwdText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwdText.transformationMethod = PasswordTransformationMethod.getInstance()
        pwdcheckText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwdcheckText.transformationMethod = PasswordTransformationMethod.getInstance()

        pwdText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        /*
        pwdBtn.setOnClickListener{
            if(pwdText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                pwdText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            else{
                pwdText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

         */

        pwdcheckText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        /*
        pwdcheckBtn.setOnClickListener{
            if(pwdcheckText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
                pwdcheckText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            else{
                pwdcheckText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

         */

        var error = false
        joinBtn.setOnClickListener{


            var pattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
            if(emailText.text.toString()=="" || !pattern.matcher(emailText.text.toString()).matches()){
                //email err
                emailErr.text = "이메일을 정확하게 입력해주세요."
                emailErr.visibility=View.VISIBLE
                error = true
            }
            else{
                emailErr.text = "이메일을 정확하게 입력해주세요."
                emailErr.visibility=View.GONE
            }
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
                //서버 연동 (이메일 중복 확인)
                var signup: HashMap<String, String> = HashMap()
                signup.put("email", emailText.text.toString())
                val intent = Intent(this, NicknameActivity::class.java)

                apiService.emailcheckAPI(signup)?.enqueue(object : Callback<Post?> {
                    override fun onFailure(call: Call<Post?>, t: Throwable) {
                        Log.d(ContentValues.TAG, "실패 : {${t}}")
                    }
                    override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                        Log.i("중복이메일API: ",response.message())
                        if(response.body()?.success==true){
                            Log.i("중복 이메일 확인: ","success")
                            intent.putExtra("email",emailText.text.toString())
                            intent.putExtra("password",pwdText.text.toString())
                            startActivity(intent)
                        }
                        else{
                            Log.i("join","fail")
                            Log.i("join",response.code().toString())
                            if(response.code()==400){
                                emailErr.text = "이미 사용중인 이메일입니다."
                                emailErr.visibility=View.VISIBLE
                            }
                        }
                    }
                })

            }

        }

    }

}