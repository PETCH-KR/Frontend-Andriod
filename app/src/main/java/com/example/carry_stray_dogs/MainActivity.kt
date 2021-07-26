package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentHome by lazy{Fragment_home()}
    private val fragmentlist by lazy{Fragment_list()}
    private val fragmentchat by lazy{Fragment_chat()}
    private val fragmentMypage by lazy{Fragment_mypage()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        initNavigationBar()
    }

    private fun initNavigationBar(){
        bottom_navigation.run{
            setOnNavigationItemSelectedListener{
                when(it.itemId){
                    R.id.home->{
                        changeFragment(fragmentHome)
                    }
                    R.id.list->{
                        changeFragment(fragmentlist)
                    }
                    R.id.chat->{
                        changeFragment(fragmentchat)
                    }
                    R.id.mypage->{
                        changeFragment(fragmentMypage)
                    }
                }
                true
            }
            selectedItemId = R.id.home
        }
    }

    private fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}