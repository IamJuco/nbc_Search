package com.example.nbc_search.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class SearchModel (
    val thumbnailUrl: String,
    val siteName: String,
    val dateTime: Date,
    val favorite: Int
) : Parcelable