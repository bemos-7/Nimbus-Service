package com.bemos.nimbus.presentation.di.module

import com.bemos.nimbus.domain.use_cases.DeleteFileUseCase
import com.bemos.nimbus.domain.use_cases.DownloadFileUseCase
import com.bemos.nimbus.domain.use_cases.GetKeyUseCase
import com.bemos.nimbus.domain.use_cases.GetListFiles
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.SetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.UploadFileUseCase
import com.bemos.nimbus.presentation.list_of_files.vm.ListOfFilesViewModel
import com.bemos.nimbus.presentation.list_of_files.vm.factory.ListOfFilesViewModelFactory
import com.bemos.nimbus.presentation.main_activity.vm.factory.MainViewModelFactory
import com.bemos.nimbus.presentation.on_board.vm.factory.OnBoardViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideOnBoardViewModelFactory(
        getKeyUseCase: GetKeyUseCase,
        setSharedKeyUseCase: SetSharedKeyUseCase
    ): OnBoardViewModelFactory {
        return OnBoardViewModelFactory(
            getKeyUseCase = getKeyUseCase,
            setSharedKeyUseCase = setSharedKeyUseCase
        )
    }

    @Provides
    fun provideListOfFilesViewModelFactory(
        getListFiles: GetListFiles,
        getSharedKeyUseCase: GetSharedKeyUseCase,
        downloadFileUseCase: DownloadFileUseCase,
        uploadFileUseCase: UploadFileUseCase,
        deleteFileUseCase: DeleteFileUseCase
    ): ListOfFilesViewModelFactory {
        return ListOfFilesViewModelFactory(
            getListFiles = getListFiles,
            getSharedKeyUseCase = getSharedKeyUseCase,
            downloadFileUseCase = downloadFileUseCase,
            uploadFileUseCase = uploadFileUseCase,
            deleteFileUseCase = deleteFileUseCase
        )
    }

    @Provides
    fun provideMainViewModelFactory(
        getSharedKeyUseCase: GetSharedKeyUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getSharedKeyUseCase = getSharedKeyUseCase
        )
    }
}