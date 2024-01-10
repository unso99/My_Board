package com.myboard.domain.usecase

import com.myboard.domain.model.Content
import com.myboard.data.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun save(item: Content) = contentRepository.save(item)

    suspend fun delete(item: Content) = contentRepository.delete(item)

    suspend fun addLikeCount(item: Content) = contentRepository.addLikeCount(item)

    fun loadList() = contentRepository.loadList()
}