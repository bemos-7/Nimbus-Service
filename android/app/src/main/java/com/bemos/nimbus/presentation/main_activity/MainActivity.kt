package com.bemos.nimbus.presentation.main_activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bemos.nimbus.domain.use_cases.SetSharedKeyUseCase
import com.bemos.nimbus.presentation.di.app_component.appComponent
import com.bemos.nimbus.presentation.list_of_files.ListOfFilesContent
import com.bemos.nimbus.presentation.list_of_files.ListOfFilesScreen
import com.bemos.nimbus.presentation.list_of_files.vm.factory.ListOfFilesViewModelFactory
import com.bemos.nimbus.presentation.main_activity.vm.MainViewModel
import com.bemos.nimbus.presentation.main_activity.vm.factory.MainViewModelFactory
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

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val mainViewModel = viewModel<MainViewModel>(
                factory = mainViewModelFactory
            )
            val sharedKey by mainViewModel.keyShared.collectAsState()
            val startDestination = rememberStartDestination(sharedKey)
            NimbusTheme {
                NavHost(
                    navController = navController,
                    startDestination = startDestination
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

    private fun rememberStartDestination(sharedKey: String): String {
        return if (sharedKey.isNotEmpty()) {
            NAV_LIST_OF_FILES
        } else {
            NAV_ON_BOARD
        }
    }
}
