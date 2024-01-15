package lt.vianet.musicapp.modules.features.music.state

import lt.vianet.musicapp.modules.data.model.music.MusicCategory

sealed class MusicCategoriesState {
    data object Initial : MusicCategoriesState()
    data object Loading : MusicCategoriesState()
    data class Success(val musicCategories: List<MusicCategory>) : MusicCategoriesState()
    data object Error : MusicCategoriesState()
}
