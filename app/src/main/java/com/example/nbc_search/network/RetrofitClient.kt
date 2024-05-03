package com.example.nbc_search.network

import com.example.nbc_search.Constants
import com.example.nbc_search.data.KakaoSearchAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitClient 객체를 매번 생성하면 high cost -> 싱글톤으로 실행
object RetrofitClient {

    // 네트워크 요청을 위한 httpClient 구성 ( 네트워크 요청을 실제로 수행 )
    private val okHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    // retrofit 객체 초기화및 생성 ( retrofit 인스턴스 생성및 데이터 파싱 )
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit ( KakaoSearchAPI의 API 호출을 수행할 수 있게 해줌 )
    val searchImage: KakaoSearchAPI by lazy {
        retrofit.create(KakaoSearchAPI::class.java)
    }
}