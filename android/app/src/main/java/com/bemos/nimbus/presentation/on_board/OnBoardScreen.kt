package com.bemos.nimbus.presentation.on_board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bemos.nimbus.presentation.on_board.vm.OnBoardViewModel
import com.bemos.nimbus.presentation.on_board.vm.factory.OnBoardViewModelFactory
import com.bemos.nimbus.shared.Constants.NAV_LIST_OF_FILES

@Composable
fun OnBoardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onBoardViewModelFactory: OnBoardViewModelFactory
) {
    val viewModel = viewModel<OnBoardViewModel>(
        factory = onBoardViewModelFactory
    )

    val key by viewModel.key.collectAsState()

    OnBoardContent(
        uuid = key,
        onStartClick = {
            viewModel.getKey()
        },
        onKeyClick = {
            viewModel.setSharedKey(it)
            navController.popBackStack()
            navController.navigate(NAV_LIST_OF_FILES)
        }
    )
}