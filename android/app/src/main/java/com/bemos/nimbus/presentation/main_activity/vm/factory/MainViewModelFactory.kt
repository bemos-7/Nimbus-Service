package com.bemos.nimbus.presentation.main_activity.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.presentation.main_activity.vm.MainViewModel

class MainViewModelFactory(
    private val getSharedKeyUseCase: GetSharedKeyUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getSharedKeyUseCase = getSharedKeyUseCase
        ) as T
    }
}