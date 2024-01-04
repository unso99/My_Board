package com.myboard.data.model.dto

//api 통신 처리 결과 dto
data class ListResponse( //get을 통해 얻어올때
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: List<ContentDto>
)

data class ContentResponse( //단일의 데이터를 수정, 저장, 삭제할때
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: ContentDto? = null
)
