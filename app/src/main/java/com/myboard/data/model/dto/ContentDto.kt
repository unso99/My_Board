package com.myboard.data.model.dto

import android.util.Base64
import java.util.Date

//api 통신을 통해 가져오는 dto
data class ContentDto(
    val id: Int? = null,
    val nickname :String,
    val title: String,
    val content: String,
    val createdDate: Date? = null,
    val likeCount: Int? = null,
    val viewCount: Int? = null,
    val img : String? = ""
)
