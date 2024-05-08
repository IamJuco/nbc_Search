package com.example.nbc_search

import com.example.nbc_search.Constants.Companion.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Constants {
    companion object {
        // kakaoAPI
        const val BASE_URL = "https://dapi.kakao.com"
        const val AUTH_HEADER = "2d6d59a869963226bc8390c125b99056"

        // 날짜
        const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

        // favorite 데이터 이름
        const val FAVORITE_DATA = "favorite_data"

        // 검색 저장 데이터 이름
        const val SEARCH_DATA = "search_data"
        // 검색 저장 키
        const val SEARCH_DATA_KEY = "search_data_key"
    }
}

object FormatManager {
    fun dateFormat(date: Date): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(date)
    }
}