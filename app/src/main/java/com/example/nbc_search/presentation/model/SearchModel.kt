package com.example.nbc_search.presentation.model

import java.util.Date

data class SearchModel (
    val thumbnailUrl: String,
    val siteName: String,
    val dateTime: Date,
    var favorite: Boolean = false
)