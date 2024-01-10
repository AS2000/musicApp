package lt.vianet.musicapp.modules.data.repository.mockHelper.parser

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import lt.vianet.musicapp.modules.common.helper.EnumJsonHelper
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoriesResponse
import lt.vianet.musicapp.modules.data.model.music.response.MusicItemsResponse

object ParseJsonMusicItemsHelper {
    fun parseJsonMusicCategories(jsonString: String): MusicCategoriesResponse? {
        val moshi: Moshi = Moshi.Builder()
            .add(EnumJsonHelper.createEnumJsonAdapter<CategoryType>())
            .build()

        val jsonAdapter: JsonAdapter<MusicCategoriesResponse> =
            moshi.adapter(MusicCategoriesResponse::class.java)

        return jsonAdapter.fromJson(jsonString)
    }

    fun parseJsonMusicItems(jsonString: String): MusicItemsResponse? {
        val moshi: Moshi = Moshi.Builder()
            .build()

        val jsonAdapter: JsonAdapter<MusicItemsResponse> =
            moshi.adapter(MusicItemsResponse::class.java)

        return jsonAdapter.fromJson(jsonString)
    }
}
