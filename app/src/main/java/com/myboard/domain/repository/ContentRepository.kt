package com.myboard.domain.repository

import com.myboard.domain.model.Content

interface ContentRepository {

    suspend fun save(item : Content) : Boolean
}