package com.bemos.nimbus.presentation.list_of_files.utils.ui.pull_refresh

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.presentation.list_of_files.utils.ui.list_item.FileItem
import com.bemos.nimbus.ui.theme.LightBlack
import com.bemos.nimbus.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPullRefreshLazyColumn(
    items: List<FileModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onDownloadClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = LightBlack,
                color = LightBlue,
                state = state
            )
        }
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(items) {
                FileItem(
                    file = it,
                    onDownloadClick = onDownloadClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}