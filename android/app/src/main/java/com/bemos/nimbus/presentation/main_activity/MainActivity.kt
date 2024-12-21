package com.bemos.nimbus.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bemos.nimbus.domain.use_cases.SetSharedKeyUseCase
import com.bemos.nimbus.presentation.di.app_component.appComponent
import com.bemos.nimbus.presentation.list_of_files.ListOfFilesContent
import com.bemos.nimbus.presentation.list_of_files.ListOfFilesScreen
import com.bemos.nimbus.presentation.list_of_files.vm.factory.ListOfFilesViewModelFactory
import com.bemos.nimbus.presentation.on_board.OnBoardScreen
import com.bemos.nimbus.presentation.on_board.vm.factory.OnBoardViewModelFactory
import com.bemos.nimbus.shared.Constants.NAV_LIST_OF_FILES
import com.bemos.nimbus.shared.Constants.NAV_ON_BOARD
import com.bemos.nimbus.ui.theme.NimbusTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var onBoardViewModelFactory: OnBoardViewModelFactory

    @Inject
    lateinit var listOfFilesViewModelFactory: ListOfFilesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            appComponent.inject(this)
            val navController = rememberNavController()
            NimbusTheme {
                NavHost(
                    navController = navController,
                    startDestination = NAV_ON_BOARD
                ) {
                    composable(
                        route = NAV_ON_BOARD
                    ) {
                        OnBoardScreen(
                            navController = navController,
                            onBoardViewModelFactory = onBoardViewModelFactory
                        )
                    }
                    composable(
                        route = NAV_LIST_OF_FILES
                    ) {
                        ListOfFilesScreen(
                            listOfFilesViewModelFactory = listOfFilesViewModelFactory
                        )
                    }
                }
            }
        }
    }
}
