package com.example.carry_stray_dogs

import android.app.Application
import androidx.annotation.Nullable
import com.kakao.auth.*


class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        // Kakao Sdk 초기화
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    inner class KakaoSDKAdapter : KakaoAdapter() {
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    // Kakao SDK로그인을 하는 방식에 대한 Enum class (카카오톡 앱 + 카카오 스토리 + 웹뷰 다이어로그 포함)
                    return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                }

                override fun isSecureMode(): Boolean {
                    return false
                }

                @Nullable
                override fun getApprovalType(): ApprovalType? {
                    return ApprovalType.INDIVIDUAL
                }

                override fun isSaveFormData(): Boolean {
                    return true
                }
            }
        }

        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig { getInstance() }
        }
    }

    companion object {
        private var instance: GlobalApplication? = null
        fun getInstance(): Application? {
            checkNotNull(instance) { "this app illegal state" }
            return instance
        }
    }
}