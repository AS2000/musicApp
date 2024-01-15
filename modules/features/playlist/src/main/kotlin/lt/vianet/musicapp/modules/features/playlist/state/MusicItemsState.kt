package lt.vianet.musicapp.modules.features.playlist.state

import lt.vianet.musicapp.modules.data.model.music.MusicItem

sealed class MusicItemsState {
    data object Initial : MusicItemsState()
    data object Loading : MusicItemsState()
    data class Success(val musicItems: List<MusicItem>) : MusicItemsState()
    data object Error : MusicItemsState()
}
