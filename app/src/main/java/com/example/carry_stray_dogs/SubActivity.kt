package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carry_stray_dogs.RetrofitAPI.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubActivity : AppCompatActivity(), View.OnClickListener {

    val service: RetrofitAPI = initService()

    private fun initService(): RetrofitAPI =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitAPI::class.java)

    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        textView = findViewById(R.id.textView)

        var intent = getIntent()
        textView.text = intent.getStringExtra("text")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_in_left)
            }
        }
    }
}