package lt.vianet.musicapp.modules.data.repository.music

import android.content.Context
import android.util.Log
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoriesResponse
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoryResponse
import lt.vianet.musicapp.modules.data.network.service.MusicService
import lt.vianet.musicapp.modules.data.repository.mockHelper.MusicItemsMockHelper
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val context: Context,
    private val musicService: MusicService,
) {
    suspend fun getMusicCategories(): MusicCategoriesResponse? {
        try {
            // TODO remove line below (Mock)
            return MusicItemsMockHelper.getMusicCategoriesMock(context = context)
//            return musicService.getMusicCategories()
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
        return null
    }

    suspend fun getMusicCategory(queryMap: Map<String, String?>? = null): MusicCategoryResponse? {
        try {
            // TODO remove line below (Mock)
            return MusicItemsMockHelper.getMusicCategoryMock(context = context)
//            return musicService.getMusicCategory(queryMap)
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
        return null
    }
}
