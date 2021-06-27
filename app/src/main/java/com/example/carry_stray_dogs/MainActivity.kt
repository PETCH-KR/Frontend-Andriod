package com.example.carry_stray_dogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.button){
            var intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
    }
}