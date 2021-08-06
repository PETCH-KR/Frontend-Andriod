package com.example.carry_stray_dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment_chat : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    fun newInstant() : mypage_CompanyReview_Fragament
    {
        val args = Bundle()
        val frag = mypage_CompanyReview_Fragament()
        frag.arguments = args
        return frag
    }
}