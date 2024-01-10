package com.myboard.domain.model

import android.provider.ContactsContract.CommonDataKinds.Nickname
import java.io.Serializable
import java.util.Date

//클린 아키텍처를 위해 presenter가 domain에서 사용할 model
data class Content(
    val id: Int? = null,
    val nickname: String,
    val title: String,
    val content: String,
    val createdDate: Date? = Date(),
    val likeCount: Int = 0,
    val viewCount: Int = 0,
) : Serializable
