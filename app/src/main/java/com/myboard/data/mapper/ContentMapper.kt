package com.myboard.data.mapper

import com.myboard.data.model.dto.ContentDto
import com.myboard.domain.model.Content

object ContentMapper {

    //content를 dto로 변환
    fun Content.toRequest() = ContentDto(
        id = id,
        nickName = nickName,
        title = title,
        content = content,
        createdDate = createdDate,
        likeCount = likeCount,
        viewCount = viewCount
    )
}