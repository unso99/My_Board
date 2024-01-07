package com.myboard.domain.repository

import com.myboard.data.mapper.ContentMapper.toRequest
import com.myboard.data.source.remote.api.ContentService
import com.myboard.domain.model.Content
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService
) : ContentRepository{
    override suspend fun save(item: Content): Boolean {
        return try{
            contentService.saveItem(item.toRequest())
            true
        }catch (e : IOException){
            false
        }
    }
}