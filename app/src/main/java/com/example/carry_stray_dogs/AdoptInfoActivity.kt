package com.example.carry_stray_dogs

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_chooselocation.*
import kotlinx.android.synthetic.main.activity_flightsearch.endText
import kotlinx.android.synthetic.main.activity_flightsearch.startText
import kotlinx.android.synthetic.main.activity_searchresult.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class AdoptInfoActivity : Fragment() {
    private var myContext: FragmentActivity? = null
    var dog_name: String? = null
    var dog_care: String? = null

    private var fragment1: CompanyInfo_DogInfo_Fragment? = null
    private var fragment2: CompanyInfo_AdoptInfo_Fragment? = null
    private var fragment3: CompanyInfo_CompanyReview_Fragment? = null
    var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.activity_adoptinfo, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        fragment1 = CompanyInfo_DogInfo_Fragment()
        fragment2 = CompanyInfo_AdoptInfo_Fragment()
        fragment3 = CompanyInfo_CompanyReview_Fragment()
        viewPager!!.adapter = AdoptInfoActivity.FragmentAdapter(childFragmentManager)
        viewPager!!.currentItem = 0

        val tab = view.findViewById<TabLayout>(R.id.tabLayout)
        tab.setupWithViewPager(viewPager)

        //navigate hide
        val mainAct = activity as MainActivity
        mainAct.HideBottomNavigation(false)


        //fragment 데이터 전달 받는
        var bundle: Bundle = arguments as Bundle
        dog_name = bundle.getString("dog_name").toString()
        dog_care = bundle.getString("dog_care").toString()
        Log.i("DogInfo : ","dog name: $dog_name , dog_care: $dog_care")

        val backBtn : ImageButton = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener{

            val transaction = myContext!!.supportFragmentManager.beginTransaction()
            val fragment1 : Fragment = SearchResultActivity()
            val bundle2 = Bundle()
            bundle2.putString("flight_start",bundle.getString("flight_start"))
            bundle2.putString("flight_end",bundle.getString("flight_end"))
            bundle2.putString("flight_start_time",bundle.getString("flight_start_time"))
            bundle2.putString("flight_end_time",bundle.getString("flight_end_time"))
            fragment1.arguments=bundle2
            transaction.replace(R.id.container,fragment1)
            transaction.commit()

        }

        val careInfo : LinearLayout = view.findViewById(R.id.careInfo)
        //레이아웃 숨기기
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            careInfo.visibility = View.GONE
        }

        val scrollView : ScrollView = view.findViewById(R.id.scrollView)
        //scroll event : 위에 닿으면 careInfo 보여지기

        val careName : TextView = view.findViewById(R.id.careName)
        careName.text = dog_care


        return view
    }


    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

    class FragmentAdapter (fm : FragmentManager): FragmentPagerAdapter(fm) {
        //position 에 따라 원하는 Fragment로 이동시키기
        override fun getItem(position: Int): Fragment {
            val fragment =  when(position)
            {
                0->CompanyInfo_DogInfo_Fragment().newInstant()
                1->CompanyInfo_AdoptInfo_Fragment().newInstant()
                else -> CompanyInfo_CompanyReview_Fragment().newInstant()

            }
            return fragment
        }

        //tab의 개수만큼 return
        override fun getCount(): Int = 3

        //tab의 이름 fragment마다 바꾸게 하기
        override fun getPageTitle(position: Int): CharSequence? {
            val title = when(position)
            {
                0->"유기견 정보"
                1->"입양처 정보"
                else -> "기관 후기"
            }
            return title     }

    }
}