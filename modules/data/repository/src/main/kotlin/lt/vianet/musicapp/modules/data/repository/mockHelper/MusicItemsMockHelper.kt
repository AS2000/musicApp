package lt.vianet.musicapp.modules.data.repository.mockHelper

import android.content.Context
import android.util.Log
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoriesResponse
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoryResponse
import lt.vianet.musicapp.modules.data.repository.mockHelper.parser.ParseJsonMusicItemsHelper
import java.io.IOException

object MusicItemsMockHelper {
    fun getMusicCategoriesMock(context: Context): MusicCategoriesResponse? {
        val jsonString: String?

        try {
            jsonString = context.assets.open("mock/musicCategoriesResponse.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, ioException.message ?: "Can't read musicCategoriesResponse.json file")
            return null
        }

        return ParseJsonMusicItemsHelper.parseJsonMusicCategories(jsonString = jsonString)
    }

    fun getMusicCategoryMock(context: Context): MusicCategoryResponse? {
        val jsonString: String?

        try {
            jsonString = context.assets.open("mock/musicCategoryResponse.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, ioException.message ?: "Can't read musicCategoryResponse.json file")
            return null
        }

        return ParseJsonMusicItemsHelper.parseJsonMusicItems(jsonString = jsonString)
    }
}
