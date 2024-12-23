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
    private val setSharedKeyUseCase: SetSharedKeyUseCase
) : ViewModel() {

    private val _key = MutableStateFlow("")
    val key: StateFlow<String> get() = _key

    fun getKey() = viewModelScope.launch {
        val response = getKeyUseCase.execute()
        if (response.isSuccessful) {
            val token = response.body()!!.id
            _key.update {
                token
            }
        }
    }

    fun setSharedKey(
        key: String
    ) {
        setSharedKeyUseCase.execute(
            key
        )
    }
}