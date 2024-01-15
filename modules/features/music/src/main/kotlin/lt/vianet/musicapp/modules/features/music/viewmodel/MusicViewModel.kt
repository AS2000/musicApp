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
import lt.vianet.musicapp.modules.features.music.state.MusicCategoriesState
import lt.vianet.musicapp.modules.features.playlist.state.MelodiesLengthsInFilesystemState
import lt.vianet.musicapp.modules.features.playlist.state.MusicCategoryState
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    private val memoryStorage: MemoryStorage,
) : ViewModel() {

    /** Music Categories State */
    private val _musicCategoriesState: MutableStateFlow<MusicCategoriesState> =
        MutableStateFlow(MusicCategoriesState.Initial)
    val musicCategoriesState = _musicCategoriesState.asStateFlow()

    /** Music Category State */
    private val _musicCategoryState: MutableStateFlow<MusicCategoryState> =
        MutableStateFlow(MusicCategoryState.Initial)
    val musicCategoryState = _musicCategoryState.asStateFlow()

    /** Melodies Lengths in Filesystem State */
    private val _melodiesLengthInFileSystemState: MutableStateFlow<MelodiesLengthsInFilesystemState> =
        MutableStateFlow(MelodiesLengthsInFilesystemState.Initial)
    val melodiesLengthInFileSystemState = _melodiesLengthInFileSystemState.asStateFlow()

    var wasFetchedMusicCategory = false

    init {
        getMusicCategories()
        getMusicCategory()
    }

    fun getMusicCategories() {
        _musicCategoriesState.value = MusicCategoriesState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = musicRepository.getMusicCategories()

            if (!response?.data?.musicCategories.isNullOrEmpty()) {
                _musicCategoriesState.value = MusicCategoriesState.Success(
                    musicCategories = response?.data?.musicCategories as MutableList<MusicCategory>,
                )

                return@launch
            }

            _musicCategoriesState.value = MusicCategoriesState.Error
        }
    }

    fun isLoading(): Boolean =
        _musicCategoriesState.value is MusicCategoriesState.Loading || _musicCategoryState.value is MusicCategoryState.Loading

    private fun getMusicCategory() {
        _musicCategoryState.value = MusicCategoryState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = musicRepository.getMusicCategory()

            if (response?.data?.musicCategory != null) {
                _musicCategoryState.value = MusicCategoryState.Success(
                    musicCategory = response.data?.musicCategory as MusicCategory,
                )

                return@launch
            }

            _musicCategoryState.value = MusicCategoryState.Error
        }
    }

    fun getTotalMelodiesLengthsInMemory(): Int {
        val musicCategory = getMusicCategoryFromMemoryStorage() ?: return 0
        return calculateDownloadedMelodiesLength(musicCategory = musicCategory)
    }

    fun getTotalMelodiesLengthsInFilesystem(melodiesLengths: List<Int>): Int =
        melodiesLengths.sumOf { it }

    private fun calculateDownloadedMelodiesLength(musicCategory: MusicCategory): Int {
        val musicItems: List<MusicItem> =
            musicCategory.musicItems?.filter { it.isDownloaded == true && it.length != null }
                ?: return 0
        if (musicItems.isEmpty()) return 0

        return musicItems.sumOf { it.length!! }
    }

    fun setMusicCategoryToMemoryStorage(musicCategory: MusicCategory) {
        memoryStorage.setMusicCategory(musicCategory = musicCategory)
    }

    private fun getMusicCategoryFromMemoryStorage(): MusicCategory? =
        memoryStorage.getMusicCategory()

    // ------------------ Database ------------------
    fun setMusicItemsToDatabase(musicItems: List<MusicItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            musicRepository.setMusicItemsToDatabase(musicItems = musicItems)
        }
    }

    fun getTotalMelodiesLengthInFilesystem() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = musicRepository.getDownloadedItemsLengths() ?: return@launch

            if (response.isNotEmpty()) {
                _melodiesLengthInFileSystemState.value = MelodiesLengthsInFilesystemState.Success(
                    melodiesLengths = response,
                )
            }
        }
    }
}
