package com.myboard.data.source.remote.api

import com.myboard.data.model.dto.ContentDto
import com.myboard.data.model.dto.ContentResponse
import com.myboard.data.model.dto.ListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ContentService { //service 실질적으로 api통신을 하는 부분 retrofit으로 통신하는 함수들 정리

    @GET("post")
    suspend fun getList(): ListResponse

    @POST("post")
    suspend fun saveItem(@Body contentDto: ContentDto): ContentResponse

    @PATCH("post")
    suspend fun updateItem(@Body contentDto: ContentDto): ContentResponse

    @DELETE("post/{id}")
    suspend fun deleteItem(@Path("id") id: Int): ContentResponse

    @POST("post/{id}/like")
    suspend fun addLikeCount(@Path("id") id : Int) : ContentResponse
}