package lt.vianet.musicapp.modules.data.repository.music

import android.content.Context
import android.util.Log
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoriesResponse
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoryResponse
import lt.vianet.musicapp.modules.data.network.service.MusicService
import lt.vianet.musicapp.modules.data.repository.mockHelper.MusicItemsMockHelper
import lt.vianet.musicapp.modules.data.storage.database.music.MusicDatabase
import lt.vianet.musicapp.modules.data.storage.database.music.entities.MusicItemEntity
import lt.vianet.musicapp.modules.data.storage.database.music.mappers.MusicItemEntityMapper
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val musicDatabase: MusicDatabase,
    private val context: Context,
    private val musicService: MusicService,
) {

    private var categoryResponse: MusicCategoryResponse? = null
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
            val musicItemEntities: List<MusicItemEntity> =
                musicDatabase.getMusicItemDao().getMusicItemEntities()

            var musicItems: List<MusicItem>? =
                MusicItemEntityMapper.mapToMusicItem(musicItemEntities = musicItemEntities)

            if (categoryResponse == null) {
//                categoryResponse = musicService.getMusicCategory(queryMap)

                // TODO remove lines below when Backend will work
                val musicCategoryMock = MusicItemsMockHelper.getMusicCategoryMock(context = context)

                if (!musicCategoryMock?.data?.musicCategory?.musicItems.isNullOrEmpty()) {
                    categoryResponse = musicCategoryMock
                }
            }

            return categoryResponse
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
        return null
    }

    suspend fun setMusicItemsToDatabase(musicItems: List<MusicItem>) {
        try {
            val musicItemEntities =
                MusicItemEntityMapper.mapToMusicItemEntities(musicItems = musicItems)

            musicDatabase.getMusicItemDao().setMusicItems(musicItemEntities = musicItemEntities)
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
    }

    suspend fun getDownloadedItemsLengths(): List<Int>? {
        try {
            return musicDatabase.getMusicItemDao().getDownloadedItemsLengths()
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
        return null
    }

    suspend fun getAllMusicItems(): List<MusicItem>? {
        try {
            val musicItemEntities: List<MusicItemEntity> =
                musicDatabase.getMusicItemDao().getMusicItemEntities()

            return MusicItemEntityMapper.mapToMusicItem(musicItemEntities = musicItemEntities)
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
        return null
    }

    suspend fun updateMusicItem(itemId: Int) {
        try {
            musicDatabase.getMusicItemDao().markAsDownloadedMusicItem(itemId = itemId)
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
    }
}
