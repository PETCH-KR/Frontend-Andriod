package com.example.carry_stray_dogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.button){
            var intent = Intent(this, SubActivity::class.java)
            intent.putExtra("text",editText.text.toString())
            startActivity(intent)
        }
    }
}