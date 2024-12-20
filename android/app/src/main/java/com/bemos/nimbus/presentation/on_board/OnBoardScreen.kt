package com.bemos.nimbus.presentation.on_board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bemos.nimbus.presentation.on_board.vm.OnBoardViewModel
import com.bemos.nimbus.presentation.on_board.vm.factory.OnBoardViewModelFactory

@Composable
fun OnBoardScreen(
    modifier: Modifier = Modifier,
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

        }
    )
}