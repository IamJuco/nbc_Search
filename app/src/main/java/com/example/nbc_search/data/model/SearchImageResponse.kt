package com.example.nbc_search.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

// API로 부터 받은 응답을 저장할 클래스 정의
data class SearchImageResponse(
    // 전체 API 응답
    @SerializedName("meta") val meta: MetaResponse?, // 응답 메타데이터
    @SerializedName("documents") val documents: List<ImageDocumentResponse>? // 실제 이미지 정보를 포함하는 리스트
)
    // 메타데이터 정보
data class MetaResponse(
    @SerializedName("total_count") val totalCount: Int?,// 검색된 전체 결과 수 ( 검색된 문서 수 )
    @SerializedName("pageable_count") val pageableCount: Int?, // total_count중 노출 가능한 문서 수
    @SerializedName("is_end") val isEnd: Boolean? // 현재 페이지가 마지막 페이지인지 여부, 만약 값이 false면 page를 증가시켜 다음 페이지 요청 가능
)
    // 각 이미지에 대한 상세 정보
data class ImageDocumentResponse(
    @SerializedName("collection") val collection: String?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("display_sitename") val displaySitename: String?,
    @SerializedName("doc_url") val docUrl: String?,
    @SerializedName("datetime") val datetime: Date?
)
