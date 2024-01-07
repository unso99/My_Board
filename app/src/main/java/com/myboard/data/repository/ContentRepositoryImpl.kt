package com.myboard.data.repository

import com.myboard.data.mapper.ContentMapper.toContent
import com.myboard.data.mapper.ContentMapper.toEntity
import com.myboard.data.mapper.ContentMapper.toRequest
import com.myboard.data.source.local.dao.ContentDao
import com.myboard.data.source.remote.api.ContentService
import com.myboard.domain.model.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao
) : ContentRepository {
    override suspend fun save(item: Content): Boolean {
        return try {
            contentService.saveItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun update(item: Content): Boolean {
        return try {
            contentService.updateItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun delete(item: Content): Boolean {
        return try {
            item.id?.let { id ->
                contentService.deleteItem(id)
            }
            contentDao.delete(item.toEntity())
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun loadList(): Flow<List<Content>> {
        return flow {
//            contentDao.selectAll().collect{list ->
//                emit(//room db 조회
//                    list.map { it.toContent() }
//                )
//            }
            emit(//api 통신을 통한 디비 조회
                try {
                    contentService.getList().data.map { it.toContent() }
                } catch (e: IOException) {
                    emptyList()
                }
            )
        }
    }
}