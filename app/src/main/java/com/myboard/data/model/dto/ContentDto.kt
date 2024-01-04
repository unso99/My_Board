package com.myboard.data.model.dto

import java.util.Date

//api 통신을 통해 가져오는 dto
data class ContentDto(
    val id: Int? = null,
    val nickName :String,
    val title: String,
    val content: String,
    val createdDate: Date? = null,
    val likeCount: Int? = null,
    val viewCount: Int? = null,
)
