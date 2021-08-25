package com.example.carry_stray_dogs

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
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity() {

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

        var joinBtn = findViewById<ImageButton>(R.id.confirmBtn)
        var emailText = findViewById<EditText>(R.id.emailText)
        var pwdText = findViewById<EditText>(R.id.pwdText)
        var pwdcheckText = findViewById<EditText>(R.id.pwdcheckText)
        var nameText = findViewById<EditText>(R.id.nameText)
        var emailErr = findViewById<TextView>(R.id.email_error_msg)
        var pwdErr = findViewById<TextView>(R.id.pwd_error_msg)
        var pwdcheckErr = findViewById<TextView>(R.id.pwdcheck_error_msg)
        var nameErr = findViewById<TextView>(R.id.name_error_msg)
        var pwdBtn = findViewById<ImageButton>(R.id.pwdBtn)
        var pwdcheckBtn = findViewById<ImageButton>(R.id.pwdcheckBtn)

        emailErr.visibility=View.GONE
        pwdErr.visibility=View.GONE
        pwdcheckErr.visibility=View.GONE
        nameErr.visibility=View.GONE

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
                emailErr.visibility=View.VISIBLE
                error = true
            }
            else{
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
            if(nameText.text.toString()==""){
                //name err
                nameErr.visibility=View.VISIBLE
                error = true
            }
            else{
                nameErr.visibility=View.GONE
            }

            if(!error){
                //서버 연동 (이메일 중복,회원가입 확인)
                Log.i("join","success")
            }

        }

    }

}