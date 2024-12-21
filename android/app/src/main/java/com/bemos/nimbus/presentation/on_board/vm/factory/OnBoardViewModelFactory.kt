package com.bemos.nimbus.presentation.on_board.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bemos.nimbus.domain.use_cases.GetKeyUseCase
import com.bemos.nimbus.presentation.on_board.vm.OnBoardViewModel

class OnBoardViewModelFactory(
    private val getKeyUseCase: GetKeyUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardViewModel(
            getKeyUseCase = getKeyUseCase
        ) as T
    }
}