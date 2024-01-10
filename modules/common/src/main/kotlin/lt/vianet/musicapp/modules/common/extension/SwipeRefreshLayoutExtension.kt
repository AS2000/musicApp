package lt.vianet.musicapp.modules.common.extension

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.onRefresh(
    isCurrentlyLoading: () -> Boolean = { false },
    listener: () -> Unit,
) {
    setOnRefreshListener {
        if (isCurrentlyLoading()) {
            isRefreshing = false

            return@setOnRefreshListener
        }

        listener()
    }
}
