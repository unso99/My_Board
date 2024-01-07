package com.myboard.domain.usecase

import com.myboard.domain.model.Content
import com.myboard.domain.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun save(item:Content) = contentRepository.save(item)

    fun loadList() = contentRepository.loadList()
}