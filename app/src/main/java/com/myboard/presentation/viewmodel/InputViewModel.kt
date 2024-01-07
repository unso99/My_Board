package com.myboard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myboard.domain.model.Content
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class InputViewModel : ViewModel() {
    private val _doneEvent = MutableLiveData<Pair<Boolean, String>>()
    val doneEvent: LiveData<Pair<Boolean, String>> = _doneEvent

    var nickname = MutableLiveData("")
    var title = MutableLiveData("")
    var content = MutableLiveData("")

    private var item: Content? = null

    fun initItem(item: Content) {
        this.item = item
        nickname.value = item.nickName
        title.value = item.title
        content.value = item.content
    }

    fun insertData(){
        val nicknameValue = nickname.value
        val titleValue = title.value
        val contentValue= content.value

        if(nicknameValue.isNullOrBlank() ||
            titleValue.isNullOrBlank() ||
             contentValue.isNullOrBlank()){
            _doneEvent.value = Pair(false,"모든 항목을 입력하셔야 합니다.")
            return
        }
    }
}