package com.example.carry_stray_dogs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class MyPagerAdapter(var context: Context) : PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view : View ?=null;
        var inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.pager_item,container,false);
        var imageView = view.findViewById<ImageView>(R.id.imageView)
        var goBtn = view.findViewById<ImageButton>(R.id.playbtn)
        var textView : TextView = view.findViewById(R.id.txt)
        var text : TextView = view.findViewById(R.id.textView)

        goBtn.setOnClickListener {
            context.startActivity( Intent(context, LoginActivity::class.java))
        }

        if(position==0){
            imageView.setImageResource(R.drawable.splash_1);
            textView.text="해외 이동 봉사란?"
            text.text="국내에서 구조된 소중한 아이들.\n그 중 해외로 입양된 아이들을 안전하게\n가족들에게 데려다 주는 것을 말해요."
            goBtn.visibility = View.INVISIBLE
        }
        else if(position==1){
            imageView.setImageResource(R.drawable.splash_2);
            textView.text="나의 상황에 맞는 봉사하기"
            text.text="내가 데려갈 수 있는 아이는?\n나의 항공 스케줄과 맞는\n아이들을 찾아 보세요"
            goBtn.visibility = View.INVISIBLE
        }
        else if(position==2){
            imageView.setImageResource(R.drawable.splash_3);
            textView.text="쉽고 정확한 의사소통"
            text.text="담당 입양 기관이 봉사를 수락하면\n간단한 안내와 봉사 과정을 공유하는\n채팅방이 개설될거에요!"
            goBtn.visibility = View.INVISIBLE
        }
        else {
            imageView.setImageResource(R.drawable.splash_4);
            textView.text="Petch와 함께 이동 봉사 시작해보기!"
            text.text="과연, 어떤 사랑스러운 아이들이\n여러분을 기다리고 있을까요?\n아이들이 외롭지 않도록 동행해 주세요!"
            goBtn.visibility = View.VISIBLE
        }

        container.addView(view)

        return view
    }



    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return 4
    }
}