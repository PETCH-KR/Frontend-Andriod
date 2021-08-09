package com.example.carry_stray_dogs

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    //Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl( "http://34.127.14.204/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(RetrofitAPI::class.java)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //액션바 제거
        var actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var kakaoLoginBtn = findViewById<ImageButton>(R.id.kakao_login)
        kakaoLoginBtn.setOnClickListener {

            // 카카오계정으로 로그인
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "로그인 실패", error)
                } else if (token != null) {
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")

                    var kakao_token: HashMap<String, String> = HashMap()
                    kakao_token.put("token", token.accessToken)

                    apiService.kakaosingupAPI(kakao_token)?.enqueue(object : Callback<Post?>{
                        override fun onFailure(call: Call<Post?>, t: Throwable) {
                            Log.d(TAG, "실패 : {${t}}")
                        }
                        override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                            Log.d("email : ", response.body()?.getDataList()?.email.toString())
                            Log.d("refresh_token : ", response.body()?.getDataList()?.refresh_token.toString())
                            Log.d("access_token : ", response.body()?.getDataList()?.access_token.toString())
                        }
                    })
                }

                // 사용자 정보 요청 (기본)
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 정보 요청 실패", error)
                    } else if (user != null) {
                        Log.i(TAG, "사용자 정보 요청 성공" + "\n이메일: ${user.kakaoAccount?.email}")
                    }
                }

            }


            var loginBtn = findViewById<ImageButton>(R.id.without_login)
            loginBtn.setOnClickListener() {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }


}