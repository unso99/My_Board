package com.myboard.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date


//api 통신을 통해 가져오는 data
@Entity(tableName = "Content")
data class ContentEntity(
    @PrimaryKey(false)
    val id : Int,
    @ColumnInfo
    var title : String,
    @ColumnInfo
    var content : String,
    @ColumnInfo
    val createdDate : Date,
    @ColumnInfo
    val likeCount : Int,
    @ColumnInfo
    val viewCount : Int
) : Serializable
