package com.bemos.nimbus.presentation.main_activity.vm

import androidx.lifecycle.ViewModel
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSharedKeyUseCase: GetSharedKeyUseCase
) : ViewModel() {

    private val _keyShred = MutableStateFlow("")
    val keyShared: StateFlow<String> get() = _keyShred

    init {
        getSharedKey()
    }

    private fun getSharedKey() {
        val key = getSharedKeyUseCase.execute()
        _keyShred.update {
            key
        }
    }
}