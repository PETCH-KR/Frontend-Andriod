package com.example.carry_stray_dogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class Fragment_mypage : Fragment(){

    var viewPager: ViewPager? = null
    var userImage : ImageButton ?= null
    private var fragment1: mypage_MyReview_Fragament? = null
    private var fragment2: mypage_CompanyReview_Fragament? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_mypage, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        fragment1 = mypage_MyReview_Fragament()
        fragment2 = mypage_CompanyReview_Fragament()
        viewPager!!.adapter = FragmentAdapter(childFragmentManager)
        viewPager!!.currentItem = 0

        val tab = view.findViewById<TabLayout>(R.id.tabLayout)
        tab.setupWithViewPager(viewPager)

        val logoutBtn = view.findViewById<ImageButton>(R.id.logoutBtn)
        logoutBtn.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        userImage = view.findViewById(R.id.userImageBtn)

        return view
    }

    class FragmentAdapter (fm : FragmentManager): FragmentPagerAdapter(fm) {
        //position 에 따라 원하는 Fragment로 이동시키기
        override fun getItem(position: Int): Fragment {
            val fragment =  when(position)
            {
                0->mypage_MyReview_Fragament().newInstant()
                else -> mypage_CompanyReview_Fragament().newInstant()

            }
            return fragment
        }

        //tab의 개수만큼 return
        override fun getCount(): Int = 2

        //tab의 이름 fragment마다 바꾸게 하기
        override fun getPageTitle(position: Int): CharSequence? {
            val title = when(position)
            {
                0->"나의 후기"
                else -> "기관 후기"
            }
            return title     }

    }

    fun newInstant() : mypage_CompanyReview_Fragament
    {
        val args = Bundle()
        val frag = mypage_CompanyReview_Fragament()
        frag.arguments = args
        return frag
    }

}