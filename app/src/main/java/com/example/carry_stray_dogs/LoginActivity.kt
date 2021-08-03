package com.example.carry_stray_dogs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.AuthType
import com.kakao.auth.Session

class LoginActivity : AppCompatActivity() {

    private val sessionCallback: SessionCallback = SessionCallback()
    var session: Session? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //액션바 제거
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        session = Session.getCurrentSession();
        session?.addCallback(sessionCallback);

        var kakaoLoginBtn = findViewById<ImageButton>(R.id.kakao_login)
        kakaoLoginBtn.setOnClickListener{
            if (Session.getCurrentSession().checkAndImplicitOpen()) {
                Log.d("kakaoLogin: ", "onClick: 로그인 세션살아있음");
                // 카카오 로그인 시도 (창이 안뜬다.)
                sessionCallback.requestMe();
            } else {
                Log.d("kakaoLogin: ", "onClick: 로그인 세션끝남");
                // 카카오 로그인 시도 (창이 뜬다.)
                session?.open(AuthType.KAKAO_LOGIN_ALL, this);
            }
        }

        var loginBtn = findViewById<ImageButton>(R.id.without_login)
        loginBtn.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}