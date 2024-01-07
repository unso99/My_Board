package com.myboard.data.mapper

import com.myboard.data.model.dto.ContentDto
import com.myboard.data.model.entity.ContentEntity
import com.myboard.domain.model.Content
import java.util.Date

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

    //content를 room entity로 변경
    fun Content.toEntity() = ContentEntity(
        id = id ?: -1,
        nickName = nickName,
        title = title,
        content = content,
        createdDate = createdDate,
        likeCount = likeCount,
        viewCount = viewCount
    )

    //contentDto를 content로 변경
    fun ContentDto.toContent() = Content(
        id = id ?: -1,
        nickName = nickName,
        title = title,
        content = content,
        createdDate = createdDate ?: Date(),
        likeCount = likeCount ?: 0,
        viewCount = viewCount ?: 0
    )

    //entity를 content로 변경
    fun ContentEntity.toContent() = Content(
        id = id,
        nickName = nickName,
        title = title,
        content = content,
        createdDate = createdDate,
        likeCount = likeCount,
        viewCount = viewCount
    )
}