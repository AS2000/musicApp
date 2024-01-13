package lt.vianet.musicapp.modules.features.playlist.state

import lt.vianet.musicapp.modules.data.model.music.MusicCategory

sealed class MusicCategoryState {
    data object Initial : MusicCategoryState()
    data object Loading : MusicCategoryState()
    data class Success(val musicCategory: MusicCategory) : MusicCategoryState()
    data object Error : MusicCategoryState()
}
