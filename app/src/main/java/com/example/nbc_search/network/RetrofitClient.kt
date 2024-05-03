package com.example.nbc_search.network

import com.example.nbc_search.Constants
import com.example.nbc_search.data.KakaoSearchAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// RetrofitClient 객체를 매번 생성하면 high cost -> 싱글톤으로 실행
object RetrofitClient {

    // retrofit 객체 초기화및 생성 ( retrofit 인스턴스 생성및 데이터 파싱 )
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit ( KakaoSearchAPI의 API 호출을 수행할 수 있게 해줌 )
    val searchImageRetrofit: KakaoSearchAPI by lazy {
        retrofit.create(KakaoSearchAPI::class.java)
    }

    // 네트워크 요청을 위한 httpClient 구성 ( 네트워크 요청을 실제로 수행 )
    private fun createOkHttpClient(): OkHttpClient {
        // Level를 BODY로 설정하여 네트워크 요청, 응답등 모든 내용 로그에 포함 시킴
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // 네트워크가 20초내로 요청 되지않을 시 TimeOut
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }
}
/*
private val okHttpClient by lazy {
    OkHttpClient.Builder()
        .build()
}
*/
