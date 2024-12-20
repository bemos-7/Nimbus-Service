package com.bemos.nimbus.presentation.di.module

import com.bemos.nimbus.domain.use_cases.GetKeyUseCase
import com.bemos.nimbus.presentation.on_board.vm.factory.OnBoardViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideOnBoardViewModelFactory(
        getKeyUseCase: GetKeyUseCase
    ): OnBoardViewModelFactory {
        return OnBoardViewModelFactory(
            getKeyUseCase = getKeyUseCase
        )
    }
}