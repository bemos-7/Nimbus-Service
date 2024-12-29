package com.bemos.nimbus.presentation.list_of_files.utils.ui.pull_refresh

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.PositionalThreshold
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefreshIndicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bemos.nimbus.R
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.presentation.list_of_files.utils.ui.list_item.FileItem
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
            MyCustomSnowManIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                state = state,
                isRefreshing = isRefreshing,
            )
//            Indicator(
//                modifier = Modifier.align(Alignment.TopCenter),
//                isRefreshing = isRefreshing,
//                containerColor = LightBlack,
//                color = LightBlue,
//                state = state
//            )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomSnowManIndicator(
    state: PullToRefreshState,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.pullToRefreshIndicator(
            state = state,
            isRefreshing = isRefreshing,
            containerColor = PullToRefreshDefaults.containerColor,
            threshold = PositionalThreshold
        ),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = isRefreshing,
            animationSpec = tween(durationMillis = 100),
            modifier = Modifier.align(Alignment.Center)
        ) { refreshing ->
            if (refreshing) {
                CircularProgressIndicator(Modifier.size(16.dp))
            } else {
                val distanceFraction = state.distanceFraction.coerceIn(0f, 1f)
                val rotation = distanceFraction * 180f
                Icon(
                    painter = painterResource(id = R.drawable.baseline_ac_unit_24),
                    contentDescription = "Refresh",
                    tint = LightBlue,
                    modifier = Modifier
                        .size(25.dp)
                        .graphicsLayer {
                            this.alpha = distanceFraction
                            this.scaleX = distanceFraction
                            this.scaleY = distanceFraction
                        }
                        .rotate(rotation)
                )
            }
        }
    }
}