package com.example.carry_stray_dogs

import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.usermgmt.response.model.Profile
import com.kakao.util.OptionalBoolean
import com.kakao.util.exception.KakaoException


class SessionCallback : ISessionCallback {
    // 로그인에 성공한 상태
    override fun onSessionOpened() {
        requestMe()
    }

    // 로그인에 실패한 상태
    override fun onSessionOpenFailed(exception: KakaoException) {
        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.message)
    }

    // 사용자 정보 요청
    fun requestMe() {
        UserManagement.getInstance()
            .me(object : MeV2ResponseCallback() {
                override fun onSessionClosed(errorResult: ErrorResult) {
                    Log.e("KAKAO_API", "세션이 닫혀 있음: $errorResult")
                }

                override fun onFailure(errorResult: ErrorResult) {
                    Log.e("KAKAO_API", "사용자 정보 요청 실패: $errorResult")
                }

                override fun onSuccess(result: MeV2Response) {

                    if(result.kakaoAccount.email!=null){
                        val email = result.kakaoAccount.email
                        Log.i("KAKAO_API_EMAIL :", email)

                    }
                    else{
                        Log.i("KAKAO_API", "사용자 이메일이 존재하지 않음")
                    }
                }
            })
    }
}