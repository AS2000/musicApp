package lt.vianet.musicapp.modules.features.music.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.common.extension.onRefresh
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.features.music.R
import lt.vianet.musicapp.modules.features.music.databinding.FragmentMusicBinding
import lt.vianet.musicapp.modules.features.music.state.MusicItemsState
import lt.vianet.musicapp.modules.features.music.ui.adapter.MusicCategoriesAdapter
import lt.vianet.musicapp.modules.features.music.viewmodel.MusicViewModel

@AndroidEntryPoint
class MusicFragment : Fragment(R.layout.fragment_music) {
    private val viewBinding: FragmentMusicBinding by viewBinding(FragmentMusicBinding::bind)
    private val musicViewModel by viewModels<MusicViewModel>()

    private val musicCategoriesAdapter = MusicCategoriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
        setupUI()
    }

    private fun setupListeners() {}

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewBinding) {
                    musicViewModel.musicCategoriesState.collect { state ->
                        when (state) {
                            is MusicItemsState.Loading -> {
                                swipeRefreshLayoutMusic.isRefreshing = true
                            }

                            is MusicItemsState.Success -> {
                                swipeRefreshLayoutMusic.isRefreshing = false
                                updateUI(musicCategories = state.musicCategories)
                            }

                            else -> {
                                swipeRefreshLayoutMusic.isRefreshing = false
                            }
                        }
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
    }

    private fun updateUI(musicCategories: List<MusicCategory>) {
        musicCategoriesAdapter.setItems(items = musicCategories)

        // TODO refactor lines below
        updateMemoryMelodyLength(melodyLength = 3601)
        updateFileSystemMelodyLength(melodyLength = 360103)
    }

    private fun updateMemoryMelodyLength(melodyLength: Int = 0) {
        viewBinding.memoryUsageViewMemory.setMelodyLength(melodyLength = melodyLength)
    }

    private fun updateFileSystemMelodyLength(melodyLength: Int = 0) {
        viewBinding.memoryUsageViewFileSystem.setMelodyLength(melodyLength = melodyLength)
    }
}
