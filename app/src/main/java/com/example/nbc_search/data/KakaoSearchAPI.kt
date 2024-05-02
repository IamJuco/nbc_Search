package com.example.nbc_search.data

import com.example.nbc_search.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoSearchAPI {
    @Headers("Authorization: KakaoAK ${Constants.AUTH_HEADER}")
    @GET("v2/search/image")
    suspend fun searchImage(
        // 검색을 원하는 질의어
        @Query("query") query: String,
        // 결과 문서 정렬 방식
        // accuracy(정확도순), recency(최신순), 기본값 = accuracy
        @Query("sort") sort: String,
        // 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        @Query("page") page: Int,
        // 한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80
        @Query("size") size: Int
    ) : SearchImageResponse
}