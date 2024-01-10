package com.myboard.domain.usecase

import com.myboard.domain.model.Content
import com.myboard.data.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun save(item: Content) = contentRepository.save(item)

    suspend fun update(item : Content) = contentRepository.update(item)

    suspend fun delete(item: Content) = contentRepository.delete(item)

    suspend fun plusLikeCount(item: Content) = contentRepository.plusLikeCount(item)

    suspend fun plusViewCount(item : Content) = contentRepository.plusViewCount(item)

    fun loadList() = contentRepository.loadList()
}