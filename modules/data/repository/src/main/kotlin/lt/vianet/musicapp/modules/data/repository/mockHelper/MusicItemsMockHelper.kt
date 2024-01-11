package lt.vianet.musicapp.modules.data.repository.mockHelper

import android.content.Context
import android.util.Log
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoriesResponse
import lt.vianet.musicapp.modules.data.model.music.response.MusicItemsResponse
import lt.vianet.musicapp.modules.data.repository.mockHelper.parser.ParseJsonMusicItemsHelper
import java.io.IOException

object MusicItemsMockHelper {
    fun getMusicCategoriesMock(context: Context): MusicCategoriesResponse? {
        var jsonString: String? = null

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

    fun getMusicItemsMock(context: Context): MusicItemsResponse? {
        var jsonString: String? = null

        try {
            jsonString = context.assets.open("mock/musicItemsResponse.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, ioException.message ?: "Can't read musicItemsResponse.json file")
            return null
        }

        return ParseJsonMusicItemsHelper.parseJsonMusicItems(jsonString = jsonString)
    }
}