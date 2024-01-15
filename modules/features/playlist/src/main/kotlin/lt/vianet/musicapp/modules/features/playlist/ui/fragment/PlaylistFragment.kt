package lt.vianet.musicapp.modules.features.playlist.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.common.constant.Navigation
import lt.vianet.musicapp.modules.common.extension.gone
import lt.vianet.musicapp.modules.common.extension.visible
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.features.playlist.R
import lt.vianet.musicapp.modules.features.playlist.databinding.FragmentPlaylistBinding
import lt.vianet.musicapp.modules.features.playlist.state.MusicItemsState
import lt.vianet.musicapp.modules.features.playlist.ui.adapter.PlaylistAdapter
import lt.vianet.musicapp.modules.features.playlist.viewmodel.PlaylistViewModel

@AndroidEntryPoint
class PlaylistFragment : Fragment(R.layout.fragment_playlist) {
    private val viewBinding: FragmentPlaylistBinding by viewBinding(FragmentPlaylistBinding::bind)
    private val playlistViewModel by viewModels<PlaylistViewModel>()

    private var playListScreenType: PlayListScreenType = PlayListScreenType.MEMORY
    private var categoryType: CategoryType = CategoryType.ROCK

    private val playlistAdapter = PlaylistAdapter() { itemId -> onItemSaveClicked(itemId = itemId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFragmentArgs()
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

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewBinding) {
                    playlistViewModel.musicItemsState.collect { state ->
                        when (state) {
                            is MusicItemsState.Loading -> progressBar.visible()
                            is MusicItemsState.Success -> {
                                progressBar.gone()
                                updateAdapterItems(items = state.musicItems)
                            }

                            is MusicItemsState.Error -> progressBar.gone()

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun setupUI() {
        setupTopBar()

        with(viewBinding) {
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

        updateAdapter()
    }

    private fun setupTopBar() {
        with(viewBinding.playlistToolbar) {
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            title = when (playListScreenType) {
                PlayListScreenType.MEMORY -> context.resources.getString(
                    lt.vianet.musicapp.modules.common.R.string.playlist_screen_toolbar_title_memory,
                )

                PlayListScreenType.FILE_SYSTEM -> context.resources.getString(
                    lt.vianet.musicapp.modules.common.R.string.playlist_screen_toolbar_title_filesystem,
                )

                else -> categoryType.name
            }
        }
    }

    private fun updateAdapter() {
        playlistAdapter.setPlayListScreenType(playListScreenType = playListScreenType)

        when (playListScreenType) {
            PlayListScreenType.MEMORY -> getMusicCategoryFromMemoryStorage()
            PlayListScreenType.FILE_SYSTEM -> getMusicItemsFromFilesystemStorage()
            else -> getMusicCategoryFromMemoryStorage()
        }
    }

    private fun onItemSaveClicked(itemId: Int) {
        when (playListScreenType) {
            PlayListScreenType.MEMORY -> {
                playlistViewModel.updateMusicCategoryInMemoryStorage(itemId = itemId)
                getMusicCategoryFromMemoryStorage()
            }

            PlayListScreenType.FILE_SYSTEM -> {
                playlistViewModel.updateMusicItemInFilesystemStorage(itemId = itemId)
                getMusicItemsFromFilesystemStorage()
            }

            else -> {}
        }
    }

    private fun getMusicCategoryFromMemoryStorage() {
        val musicItems: List<MusicItem> =
            playlistViewModel.getMusicCategoryFromMemoryStorage()?.musicItems ?: return
        updateAdapterItems(items = musicItems)
    }

    private fun getMusicItemsFromFilesystemStorage() {
        playlistViewModel.getMusicItemsFromFilesystemStorage()
    }

    private fun updateAdapterItems(items: List<MusicItem>) {
        playlistAdapter.setItems(items = items)
    }
}
