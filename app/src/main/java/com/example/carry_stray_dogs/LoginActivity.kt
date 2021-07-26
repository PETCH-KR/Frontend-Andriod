package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var loginBtn = findViewById<ImageButton>(R.id.without_login)
        loginBtn.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}