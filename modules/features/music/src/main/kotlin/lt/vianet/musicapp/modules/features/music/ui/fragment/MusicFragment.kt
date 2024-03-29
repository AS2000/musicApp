package lt.vianet.musicapp.modules.features.music.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.common.extension.onRefresh
import lt.vianet.musicapp.modules.common.navigator.ModuleNavigatorInterface
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.features.music.R
import lt.vianet.musicapp.modules.features.music.databinding.FragmentMusicBinding
import lt.vianet.musicapp.modules.features.music.state.MusicCategoriesState
import lt.vianet.musicapp.modules.features.music.ui.adapter.MusicCategoriesAdapter
import lt.vianet.musicapp.modules.features.music.viewmodel.MusicViewModel
import lt.vianet.musicapp.modules.features.playlist.state.MelodiesLengthsInFilesystemState
import lt.vianet.musicapp.modules.features.playlist.state.MusicCategoryState
import javax.inject.Inject

@AndroidEntryPoint
class MusicFragment : Fragment(R.layout.fragment_music) {

    @Inject
    lateinit var moduleNavigator: ModuleNavigatorInterface

    private val viewBinding: FragmentMusicBinding by viewBinding(FragmentMusicBinding::bind)
    private val musicViewModel by viewModels<MusicViewModel>()

    private val musicCategoriesAdapter = MusicCategoriesAdapter() { categoryType ->
        moduleNavigator.navigateToPlaylist(
            navController = findNavController(),
            playListScreenType = PlayListScreenType.PLAYLIST,
            categoryType = categoryType,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupUI()
    }

    override fun onResume() {
        super.onResume()

        updateStoredMelodyLengths()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                musicViewModel.musicCategoriesState.collect { state ->
                    with(viewBinding) {
                        when (state) {
                            is MusicCategoriesState.Loading -> {
                                swipeRefreshLayoutMusic.isRefreshing = true
                            }

                            is MusicCategoriesState.Success -> {
                                swipeRefreshLayoutMusic.isRefreshing = false
                                updateMusicCategoriesAdapter(musicCategories = state.musicCategories)
                            }

                            else -> {
                                swipeRefreshLayoutMusic.isRefreshing = false
                            }
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewBinding) {
                    musicViewModel.musicCategoryState.collect { state ->
                        when (state) {
                            is MusicCategoryState.Loading -> {
                                swipeRefreshLayoutMusic.isRefreshing = true
                            }

                            is MusicCategoryState.Success -> {
                                swipeRefreshLayoutMusic.isRefreshing = false

                                if (!musicViewModel.wasFetchedMusicCategory) {
                                    musicViewModel.setMusicCategoryToMemoryStorage(musicCategory = state.musicCategory)

                                    val musicItems =
                                        state.musicCategory.musicItems ?: return@collect
                                    musicViewModel.setMusicItemsToDatabase(musicItems = musicItems)

                                    // TODO find out why MutableStateFlow triggers everytime onResume
                                    musicViewModel.wasFetchedMusicCategory = true
                                }
                            }

                            else -> {
                                swipeRefreshLayoutMusic.isRefreshing = false
                            }
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                musicViewModel.melodiesLengthInFileSystemState.collect { state ->
                    when (state) {
                        is MelodiesLengthsInFilesystemState.Success -> {
                            updateStoredInFileSystemMelodyLength(melodiesLengths = state.melodiesLengths)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupUI() {
        with(viewBinding) {
            with(swipeRefreshLayoutMusic) {
                onRefresh(
                    isCurrentlyLoading = { musicViewModel.isLoading() },
                ) {
                    musicViewModel.getMusicCategories()
                }
            }

            with(recyclerViewMusicCategories) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                adapter = musicCategoriesAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }

        with(viewBinding) {
            memoryUsageViewMemory.setupNavigator(
                navigate = {
                    moduleNavigator.navigateToPlaylist(
                        navController = findNavController(),
                        playListScreenType = PlayListScreenType.MEMORY,
                        categoryType = null,
                    )
                },
            )

            memoryUsageViewFileSystem.setupNavigator(
                navigate = {
                    moduleNavigator.navigateToPlaylist(
                        navController = findNavController(),
                        playListScreenType = PlayListScreenType.FILE_SYSTEM,
                        categoryType = null,
                    )
                },
            )
        }
    }

    private fun updateMusicCategoriesAdapter(musicCategories: List<MusicCategory>) {
        musicCategoriesAdapter.setItems(items = musicCategories)
    }

    private fun updateStoredMelodyLengths() {
        updateStoredInMemoryMelodyLength(melodyLength = musicViewModel.getTotalMelodiesLengthsInMemory())
        musicViewModel.getTotalMelodiesLengthInFilesystem()
    }

    private fun updateStoredInMemoryMelodyLength(melodyLength: Int = 0) {
        viewBinding.memoryUsageViewMemory.setMelodyLength(melodyLength = melodyLength)
    }

    private fun updateStoredInFileSystemMelodyLength(melodiesLengths: List<Int>?) {
        melodiesLengths ?: return

        val melodiesLength: Int =
            musicViewModel.getTotalMelodiesLengthsInFilesystem(melodiesLengths = melodiesLengths)
        viewBinding.memoryUsageViewFileSystem.setMelodyLength(melodyLength = melodiesLength)
    }
}
