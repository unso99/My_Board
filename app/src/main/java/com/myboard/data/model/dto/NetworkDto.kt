package com.myboard.data.model.dto

//api 통신 처리 결과 dto
data class ListResponse(
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: List<ContentDto>
)

data class ContentResponse(
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: ContentDto? = null
)
