package lt.vianet.musicapp.modules.features.playlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.data.repository.music.MusicRepository
import lt.vianet.musicapp.modules.data.storage.memoryStorage.MemoryStorage
import lt.vianet.musicapp.modules.features.playlist.state.MusicItemsState
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    private val memoryStorage: MemoryStorage,
) : ViewModel() {

    /** Music Items State */
    private val _musicItemsState: MutableStateFlow<MusicItemsState> =
        MutableStateFlow(MusicItemsState.Initial)
    val musicItemsState = _musicItemsState.asStateFlow()

    fun getMusicCategoryFromMemoryStorage(): MusicCategory? =
        memoryStorage.getMusicCategory()

    fun updateMusicCategoryInMemoryStorage(itemId: Int) {
        memoryStorage.updateMusicCategory(itemId = itemId)
    }

    // ------------------ Database ------------------
    fun getMusicItemsFromFilesystemStorage() {
        _musicItemsState.value = MusicItemsState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val response = musicRepository.getAllMusicItems() ?: return@launch

            if (response.isNotEmpty()) {
                _musicItemsState.value = MusicItemsState.Success(
                    musicItems = response,
                )

                return@launch
            }
            _musicItemsState.value = MusicItemsState.Error
        }
    }

    fun updateMusicItemInFilesystemStorage(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            musicRepository.updateMusicItem(itemId = itemId)
        }
    }
}
