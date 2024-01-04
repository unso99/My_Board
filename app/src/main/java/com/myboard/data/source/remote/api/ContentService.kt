package com.myboard.data.source.remote.api

import com.myboard.data.model.dto.ContentDto
import com.myboard.data.model.dto.ContentResponse
import com.myboard.data.model.dto.ListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ContentService { //service 실질적으로 api통신을 하는 부분 retrofit으로 통신하는 함수들 정리

    @GET("list")
    suspend fun getList(): ListResponse

    @POST("save")
    suspend fun saveItem(@Body contentDto: ContentDto): ContentResponse

    @POST("update")
    suspend fun updateItem(@Body contentDto: ContentDto): ContentResponse

    @DELETE("{id}")
    suspend fun deleteItem(@Path("id") id: Int): ContentResponse
}