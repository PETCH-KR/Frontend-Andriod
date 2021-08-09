package com.example.carry_stray_dogs

import com.google.gson.annotations.SerializedName
import java.util.*

class Post {

    //kakaoLogin
    @SerializedName("success")
    val success = false

    @SerializedName("message")
    val message: String? = null

    @SerializedName("data")
    private val dataList: data = data()
    fun getDataList(): data? { return dataList }

    class data {

        @SerializedName("access_token")
        val access_token: String? = null

        @SerializedName("refresh_token")
        val refresh_token: String? = null

        @SerializedName("email")
        val email: String? = null

    }

}

