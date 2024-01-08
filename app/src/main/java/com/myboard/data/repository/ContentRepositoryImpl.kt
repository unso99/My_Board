package com.myboard.data.repository

import com.myboard.data.mapper.ContentMapper.toContent
import com.myboard.data.mapper.ContentMapper.toEntity
import com.myboard.data.mapper.ContentMapper.toRequest
import com.myboard.data.source.local.dao.ContentDao
import com.myboard.data.source.remote.api.ContentService
import com.myboard.domain.model.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao
) : ContentRepository {
    override suspend fun save(item: Content): Boolean {
        return try {
            contentService.saveItem(item.toRequest()).also {
                if (it.success) {
                    //서버에 저장이 완료되었을때
                    it.data?.let { contentDto ->
                        contentDao.insert(contentDto.toEntity())
                    }
                }
            }
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun update(item: Content): Boolean {
        return try {
            contentService.updateItem(item.toRequest()).also {
                if (it.success) {
                    it.data?.let { contentDto ->
                        contentDao.insert(contentDto.toEntity())
                    }
                }
            }
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun delete(item: Content): Boolean {
        return try {
            item.id?.let { id ->
                contentService.deleteItem(id).also {
                    if (it.success) {
                        contentDao.delete(item.toEntity())
                    }
                }
            }
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun loadList(): Flow<List<Content>> {
        return flow {
            //네트워크가 없을때에도 작동하기 위함
            try {
                contentService.getList().data.also { list ->
                    contentDao.insertAll(list.map { it.toEntity() })
                }
            } finally {
                contentDao.selectAll().collect { list ->
                    emit(
                        list.map { it.toContent() }
                    )
                }
            }
        }
    }
}