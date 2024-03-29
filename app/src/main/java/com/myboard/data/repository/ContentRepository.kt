package com.myboard.data.repository

import com.myboard.domain.model.Content
import kotlinx.coroutines.flow.Flow


interface ContentRepository {

    suspend fun save(item : Content) : Boolean

    suspend fun update(item : Content) : Boolean

    suspend fun delete(item : Content)  : Boolean

    suspend fun plusLikeCount(item : Content) : Boolean

    suspend fun plusViewCount(item : Content) : Boolean

    //데이터 조회
    fun loadList() : Flow<List<Content>>
}