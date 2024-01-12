package com.myboard.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myboard.domain.model.Content
import com.myboard.domain.usecase.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val contentUseCase: ContentUseCase
) : ViewModel() {
    private val _doneEvent = MutableLiveData<Pair<Boolean, String>>()
    val doneEvent: LiveData<Pair<Boolean, String>> = _doneEvent

    var nickname = MutableLiveData("")
    var title = MutableLiveData("")
    var content = MutableLiveData("")
    var viewCount = MutableLiveData(0)
    var img = MutableLiveData("")

    private var item: Content? = null

    fun initItem(item: Content) {
        this.item = item
        nickname.value = item.nickname
        title.value = item.title
        content.value = item.content
        viewCount.value = item.viewCount
        img.value = item.img
    }

    fun insertData() {
        val nicknameValue = nickname.value
        val titleValue = title.value
        val contentValue = content.value
        val viewCountValue = viewCount.value?.plus(1)
        val imgValue = img.value
        Log.e("insertData", titleValue.toString())
        if (nicknameValue.isNullOrBlank() ||
            titleValue.isNullOrBlank() ||
            contentValue.isNullOrBlank()
        ) {
            _doneEvent.value = Pair(false, "모든 항목을 입력하셔야 합니다.")
            return
        }

        //usecase를 통해 데이터가 추가됨
        viewModelScope.launch(Dispatchers.IO) {
            item?.let {
                contentUseCase.update(
                    item!!.copy(
                        nickname = nicknameValue,
                        title = titleValue,
                        content = contentValue,
                        createdDate = null,
                        viewCount = viewCountValue,
                        img = imgValue
                    )
                )
            } ?: contentUseCase.save(
                Content(
                    nickname = nicknameValue,
                    title = titleValue,
                    content = contentValue,
                    img = imgValue
                )
            )

        }.also {
            _doneEvent.postValue(Pair(true, if (it.isActive) "완료!" else "저장할 수 없습니다."))
        }
    }
}