package com.example.nbc_search.data.remote

import com.example.nbc_search.Constants
import com.example.nbc_search.data.model.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// 카카오 API 인터페이스 정의
// Kakao 이미지 검색 API를 호출하는 HTTP 요청 정의
interface KakaoSearchAPI {
    @Headers("Authorization: KakaoAK ${Constants.AUTH_HEADER}") // API 사용 권한 확인
    @GET("v2/search/image") // 사용 권한 중 이미지 검색을 하겠다는 설정 ( URL로 보냄 )

    // URL의 쿼리 파라미터 추가
    suspend fun searchImage(
        @Query("query") query: String, // 검색을 원하는 질의어
        @Query("sort") sort: String = "accuracy", // 결과 문서 정렬 방식, accuracy(정확도순), recency(최신순), 기본값 = accuracy
        @Query("page") page: Int = 1, // 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        @Query("size") size: Int = 80 // 한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80
    ) : SearchImageResponse
}