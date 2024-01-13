package lt.vianet.musicapp.modules.features.playlist.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.common.constant.Navigation
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.common.extension.onRefresh
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.features.playlist.R
import lt.vianet.musicapp.modules.features.playlist.databinding.FragmentPlaylistBinding
import lt.vianet.musicapp.modules.features.playlist.state.MusicCategoryState
import lt.vianet.musicapp.modules.features.playlist.ui.adapter.PlaylistAdapter
import lt.vianet.musicapp.modules.features.playlist.viewmodel.PlaylistViewModel

@AndroidEntryPoint
class PlaylistFragment : Fragment(R.layout.fragment_playlist) {
    private val viewBinding: FragmentPlaylistBinding by viewBinding(FragmentPlaylistBinding::bind)
    private val playlistViewModel by viewModels<PlaylistViewModel>()

    private var playListScreenType: PlayListScreenType = PlayListScreenType.MEMORY
    private var categoryType: CategoryType = CategoryType.ROCK

    private val playlistAdapter = PlaylistAdapter() { itemId ->
        onItemSaveClicked(itemId = itemId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFragmentArgs()
        setupListeners()
        setupObservers()
        setupUI()
    }

    @Suppress("DEPRECATION")
    private fun getFragmentArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            playListScreenType =
                arguments?.getSerializable(Navigation.SCREEN_TYPE, PlayListScreenType::class.java)
                    ?: PlayListScreenType.MEMORY
            categoryType =
                arguments?.getSerializable(Navigation.CATEGORY_TYPE, CategoryType::class.java)
                    ?: CategoryType.ROCK
        } else {
            playListScreenType =
                arguments?.getSerializable(Navigation.SCREEN_TYPE) as PlayListScreenType?
                    ?: PlayListScreenType.MEMORY
            categoryType = arguments?.getSerializable(Navigation.CATEGORY_TYPE) as CategoryType?
                ?: CategoryType.ROCK
        }
    }

    private fun setupListeners() {}

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewBinding) {
                    playlistViewModel.musicCategoryState.collect { state ->
                        when (state) {
                            is MusicCategoryState.Loading -> {
                                playlistSwipeRefreshLayout.isRefreshing = true
                            }

                            is MusicCategoryState.Success -> {
                                playlistSwipeRefreshLayout.isRefreshing = false
                                updateUI(musicCategory = state.musicCategory)
                            }

                            else -> {
                                playlistSwipeRefreshLayout.isRefreshing = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupUI() {
        with(viewBinding) {
            with(playlistSwipeRefreshLayout) {
                onRefresh(
                    isCurrentlyLoading = { playlistViewModel.isLoading() },
                ) {
                    playlistViewModel.getPlaylist()
                }
            }
            with(playlistRecyclerView) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                val divider = DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL,
                )
                divider.setDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        lt.vianet.musicapp.modules.common.R.drawable.item_divider,
                    )!!,
                )
                addItemDecoration(divider)
                adapter = playlistAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }
    }

    private fun updateUI(musicCategory: MusicCategory) {
        musicCategory.musicItems ?: return

        playlistAdapter.setItems(items = musicCategory.musicItems as List<MusicItem>)
    }

    private fun onItemSaveClicked(itemId: Int) {
        Log.e(TAG, "onItemSaveClicked - itemId: $itemId")
    }
}
