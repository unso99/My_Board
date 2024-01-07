package com.myboard.domain.repository

import com.myboard.domain.model.Content
import kotlinx.coroutines.flow.Flow


interface ContentRepository {

    suspend fun save(item : Content) : Boolean

    suspend fun update(item : Content) : Boolean

    //데이터 조회
    fun loadList() : Flow<List<Content>>
}