package com.myboard.domain.repository

import com.myboard.data.mapper.ContentMapper.toEntity
import com.myboard.data.mapper.ContentMapper.toRequest
import com.myboard.data.source.local.dao.ContentDao
import com.myboard.data.source.remote.api.ContentService
import com.myboard.domain.model.Content
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao : ContentDao
) : ContentRepository{
    override suspend fun save(item: Content): Boolean {
        return try{
            contentService.saveItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        }catch (e : IOException){
            false
        }
    }

    override suspend fun update(item: Content): Boolean {
        return try {
            contentService.updateItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        }catch (e : IOException){
            false
        }
    }
}