package lt.vianet.musicapp.modules.features.music.state

import lt.vianet.musicapp.modules.data.model.music.MusicCategory

sealed class MusicItemsState {
    object Initial : MusicItemsState()
    object Loading : MusicItemsState()
    data class Success(val musicCategories: List<MusicCategory>) : MusicItemsState()
    object Error : MusicItemsState()
}
