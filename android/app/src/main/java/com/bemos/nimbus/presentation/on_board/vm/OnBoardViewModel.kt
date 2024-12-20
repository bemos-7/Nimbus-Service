package com.bemos.nimbus.presentation.on_board.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemos.nimbus.domain.use_cases.GetKeyUseCase
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.SetSharedKeyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardViewModel @Inject constructor(
    private val getKeyUseCase: GetKeyUseCase,
    private val setSharedKeyUseCase: SetSharedKeyUseCase,
    private val getSharedKeyUseCase: GetSharedKeyUseCase
) : ViewModel() {

    private val _key = MutableStateFlow("")
    val key: StateFlow<String> get() = _key

    private val _keyShred = MutableStateFlow("")
    val keyShared: StateFlow<String> get() = _keyShred

    init {
        getSharedKey()
    }

    fun getKey() = viewModelScope.launch {
        val response = getKeyUseCase.execute()
        Log.d("getKey", response.message())
        if (response.isSuccessful) {
            val token = response.body()!!.id
            _key.update {
                token
            }
        }
        Log.d("getKey", _key.value)
    }

    fun setSharedKey(
        key: String
    ) {
        setSharedKeyUseCase.execute(
            key
        )
    }

    private fun getSharedKey() {
        val key = getSharedKeyUseCase.execute()
        _keyShred.update {
            key
        }
    }

}