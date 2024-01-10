package com.myboard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myboard.domain.model.Content
import com.myboard.domain.usecase.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentUseCase: ContentUseCase
) : ViewModel(){

    private val _doneEvent = MutableLiveData<Pair<Boolean,String>>()
    val doneEvent :LiveData<Pair<Boolean, String>> = _doneEvent

    val contentList = contentUseCase.loadList()
        .stateIn(
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000),
            scope = viewModelScope
        )
    fun deleteItem(item : Content){
        viewModelScope.launch(Dispatchers.IO){
            contentUseCase.delete(item).also {
                _doneEvent.postValue(Pair(it,"삭제 완료"))
            }
        }
    }

    fun plusLikeCount(item: Content){
        viewModelScope.launch(Dispatchers.IO){
            contentUseCase.plusLikeCount(item).also {
                _doneEvent.postValue(Pair(it,"하트 추가 완료"))
            }
        }
    }

    fun plusViewCount(item : Content){
        viewModelScope.launch(Dispatchers.IO){
            contentUseCase.plusViewCount(item).also {
                _doneEvent.postValue(Pair(it,"입장!"))
            }
        }
    }
}