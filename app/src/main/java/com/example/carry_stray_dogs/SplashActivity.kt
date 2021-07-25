package com.example.carry_stray_dogs
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.myhome.siviewpager.SIViewPager


class SplashActivity : AppCompatActivity() {
    lateinit var temp : ArrayList<Drawable>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var pageradapter = MyPagerAdapter(this)
        var pager = findViewById<ViewPager>(R.id.view_pager)
        pager.setAdapter(pageradapter)

        var tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tabLayout.setupWithViewPager(pager,true)

    }


}