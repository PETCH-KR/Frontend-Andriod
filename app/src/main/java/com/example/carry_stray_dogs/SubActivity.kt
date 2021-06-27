package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        textView = findViewById<TextView>(R.id.textView)

        var intent = getIntent()
        textView.text = intent.getStringExtra("text")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.button){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}