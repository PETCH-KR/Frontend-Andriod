package com.example.carry_stray_dogs

import retrofit2.Call
import retrofit2.http.*
import kotlin.collections.HashMap

interface RetrofitAPI {

    @FormUrlEncoded
    @POST("api/user/kakao")
    fun kakaosingupAPI(@FieldMap param: HashMap<String, String>): Call<Post?>?

    @FormUrlEncoded
    @POST("api/user/signup")
    fun singupAPI(@FieldMap param: HashMap<String, String>): Call<Post?>?

    @FormUrlEncoded
    @POST("api/user/check/email")
    fun emailcheckAPI(@FieldMap param: HashMap<String, String>): Call<Post?>?

}