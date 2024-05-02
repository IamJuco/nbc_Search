package com.example.nbc_search.model

import java.util.Date

data class SearchModel (
    val thumbnailUrl: String,
    val siteName: String,
    val dateTime: Date,
    val favorite: Boolean
)