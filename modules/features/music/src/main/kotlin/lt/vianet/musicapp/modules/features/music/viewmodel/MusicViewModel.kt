package lt.vianet.musicapp.modules.features.music.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.data.repository.music.MusicRepository
import lt.vianet.musicapp.modules.data.storage.memoryStorage.MemoryStorage
import lt.vianet.musicapp.modules.features.music.state.MusicItemsState
import lt.vianet.musicapp.modules.features.playlist.state.MusicCategoryState
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    private val memoryStorage: MemoryStorage,
) : ViewModel() {

    /** Profiles State */
    private val _musicCategoriesState: MutableStateFlow<MusicItemsState> =
        MutableStateFlow(MusicItemsState.Initial)
    val musicCategoriesState = _musicCategoriesState.asStateFlow()

    /** Profiles State */
    private val _musicCategoryState: MutableStateFlow<MusicCategoryState> =
        MutableStateFlow(MusicCategoryState.Initial)
    val musicCategoryState = _musicCategoryState.asStateFlow()

    init {
        getMusicCategories()
        getPlaylist()
    }

    fun getMusicCategories() {
        _musicCategoriesState.value = MusicItemsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = musicRepository.getMusicCategories()

            if (!response?.data?.musicCategories.isNullOrEmpty()) {
                _musicCategoriesState.value = MusicItemsState.Success(
                    musicCategories = response?.data?.musicCategories as MutableList<MusicCategory>,
                )

                return@launch
            }

            _musicCategoriesState.value = MusicItemsState.Error
        }
    }

    fun isLoading(): Boolean =
        _musicCategoriesState.value is MusicItemsState.Loading || _musicCategoryState.value is MusicCategoryState.Loading

    fun getPlaylist() {
        _musicCategoryState.value = MusicCategoryState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = musicRepository.getMusicCategory()

            if (response?.data?.musicCategory != null) {
                _musicCategoryState.value = MusicCategoryState.Success(
                    musicCategory = response.data?.musicCategory as MusicCategory,
                )

                memoryStorage.setMusicCategory(musicCategory = response.data?.musicCategory)

                return@launch
            }

            _musicCategoryState.value = MusicCategoryState.Error
        }
    }

    fun getMelodyLengthInMemory(): Int {
        val musicCategory = getMusicCategoryFromMemoryStorage() ?: return 0
        return calculateDownloadedMelodiesLength(musicCategory = musicCategory)
    }

    fun getMelodyLengthInFilesystem(): Int {
        // TODO SQLite
        return 3601
    }

    private fun calculateDownloadedMelodiesLength(musicCategory: MusicCategory): Int {
        val musicItems: List<MusicItem> =
            musicCategory.musicItems?.filter { it.isDownloaded == true && it.length != null }
                ?: return 0
        if (musicItems.isEmpty()) return 0

        return musicItems.sumOf { it.length!! }
    }

    private fun getMusicCategoryFromMemoryStorage(): MusicCategory? =
        memoryStorage.getMusicCategory()
}
