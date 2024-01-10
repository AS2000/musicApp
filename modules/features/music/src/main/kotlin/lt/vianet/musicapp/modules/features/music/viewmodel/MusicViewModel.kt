package lt.vianet.musicapp.modules.features.music.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.data.repository.music.MusicRepository
import lt.vianet.musicapp.modules.features.music.state.MusicItemsState
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
) : ViewModel() {

    /** Profiles State */
    private val _musicCategoriesState: MutableStateFlow<MusicItemsState> =
        MutableStateFlow(MusicItemsState.Initial)
    val musicCategoriesState = _musicCategoriesState.asStateFlow()

    init {
        getMusicCategories()
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

    fun isLoading(): Boolean = _musicCategoriesState.value is MusicItemsState.Loading
}
