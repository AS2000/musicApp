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
import lt.vianet.musicapp.modules.features.playlist.state.MusicCategoryState
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    private val memoryStorage: MemoryStorage,
) : ViewModel() {

    /** Profiles State */
    private val _musicCategoryState: MutableStateFlow<MusicCategoryState> =
        MutableStateFlow(MusicCategoryState.Initial)
    val musicCategoryState = _musicCategoryState.asStateFlow()

    init {
        getPlaylist()
    }

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

    fun isLoading(): Boolean = _musicCategoryState.value is MusicCategoryState.Loading

    fun getMusicCategoryFromMemoryStorage(): MusicCategory? =
        memoryStorage.getMusicCategory()
}
