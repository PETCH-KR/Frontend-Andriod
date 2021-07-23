package com.example.carry_stray_dogs

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class MyPagerAdapter(var context: Context) : PagerAdapter()  {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view : View ?=null;
        var inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.pager_item,container,false);
        var imageView = view.findViewById<ImageView>(R.id.imageView)
        var textView : TextView = view.findViewById(R.id.txt)

        if(position==0){
            imageView.setImageResource(R.drawable.temp);
            textView.text=""
        }
        else if(position==1){
            imageView.setImageResource(R.drawable.temp2);
            textView.text=""
        }
        else if(position==2){
            imageView.setImageResource(R.drawable.temp3);
            textView.text=""
        }
        else {
            imageView.setImageResource(R.drawable.temp4);
            textView.text=""
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