package com.example.carry_stray_dogs

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_chooselocation.*
import kotlinx.android.synthetic.main.activity_searchresult.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AdoptInfoActivity : Fragment() {
    private var myContext: FragmentActivity? = null
    var dog_name: String? = null
    var dog_care: String? = null

    var dynamicInfo : TextView? =null
    var group : LinearLayout? =null
    var group2 : RelativeLayout? =null
    var circleView : ImageView? =null

    private var fragment1: CompanyInfo_DogInfo_Fragment? = null
    //private var fragment2: CompanyInfo_AdoptInfo_Fragment? = null
    private var fragment3: CompanyInfo_CompanyReview_Fragment? = null
    var viewPager: ViewPager? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.activity_adoptinfo, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        fragment1 = CompanyInfo_DogInfo_Fragment()
        //fragment2 = CompanyInfo_AdoptInfo_Fragment()
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
        val scrollView : ScrollView = view.findViewById(R.id.scrollView)
        val toplayout :LinearLayout = view.findViewById(R.id.toplayout)
        //scroll event : 위에 닿으면 careInfo 보여지기
        //레이아웃 숨기기
        /*
        var temp = 0
        if(temp==0 && scrollView.top==toplayout.bottom){
            temp=1
            Log.i("scroll:" ,"top")
            CoroutineScope(Dispatchers.Main).launch {
                careInfo.visibility = View.VISIBLE
                delay(2000)
                careInfo.visibility = View.GONE
                temp=0
            }
        }

         */


        val careName : TextView = view.findViewById(R.id.careName)
        careName.text = dog_care


        //deadline Dog Info
        val buttonview = view.findViewById<LinearLayout>(R.id.deadlineInfo)
        buttonview.isClickable=true

        for(i: Int in 1..10) {

            //circle crop
            circleView = ImageView(context)
            Glide.with(this).load(R.drawable.dog).circleCrop().into(circleView!!)
            circleView!!.layoutParams = LinearLayout.LayoutParams(changeDP(80), changeDP(80))


            //group : circle crop image + info text
            dynamicInfo = TextView(context)
            dynamicInfo!!.textSize = 13f
            dynamicInfo!!.text = "\n 마감일 : 2021-01-01 \n등록일 : 2021-01-01"
            dynamicInfo!!.gravity = Gravity.CENTER_HORIZONTAL
            dynamicInfo!!.layoutParams = LinearLayout.LayoutParams(changeDP(130), changeDP(80))

            group = LinearLayout(context)
            group!!.isClickable=true
            //group!!.isDuplicateParentStateEnabled=true
            group!!.orientation = LinearLayout.VERTICAL
            val group_params = LinearLayout.LayoutParams(changeDP(130), changeDP(150))
            group_params.setMargins(changeDP(5), changeDP(0), changeDP(5), changeDP(0))
            group!!.layoutParams = group_params
            group!!.setBackgroundColor(Color.WHITE)
            circleView!!.isClickable = false
            dynamicInfo!!.isClickable = false
            group!!.addView(circleView)
            group!!.addView(dynamicInfo)
            group!!.gravity = Gravity.CENTER_HORIZONTAL

            group!!.setOnClickListener {

                /*
                val transaction = myContext!!.supportFragmentManager.beginTransaction()
                val fragment1 : Fragment = AdoptInfoActivity()
                val bundle = Bundle()
                bundle.putString("flight_start",startText.text.toString())
                bundle.putString("flight_end",endText.text.toString())
                bundle.putString("flight_start_time",startDate.text.toString())
                bundle.putString("flight_end_time",endDate.text.toString())
                bundle.putString("dog_name","멍멍이")
                bundle.putString("dog_care","petch기관")
                fragment1.arguments=bundle
                transaction.replace(R.id.container,fragment1)
                transaction.commit()
                 */

            }

            buttonview.addView(group)
        }


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
                //1->CompanyInfo_AdoptInfo_Fragment().newInstant()
                else -> CompanyInfo_CompanyReview_Fragment().newInstant()

            }
            return fragment
        }

        //tab의 개수만큼 return
        override fun getCount(): Int = 2

        //tab의 이름 fragment마다 바꾸게 하기
        override fun getPageTitle(position: Int): CharSequence? {
            val title = when(position)
            {
                0->"유기견 정보"
                //1->"입양처 정보"
                else -> "기관 후기"
            }
            return title     }

    }
}

