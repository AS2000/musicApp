package lt.vianet.musicapp.modules.data.model.music.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lt.vianet.musicapp.modules.data.model.errorData.ErrorData
import lt.vianet.musicapp.modules.data.model.music.MusicCategory

@JsonClass(generateAdapter = true)
data class MusicCategoriesResponse(
    @field:Json(name = "data") val data: MusicCategoriesData? = null,
    @field:Json(name = "error_data") val errorData: ErrorData? = null,
)

@JsonClass(generateAdapter = true)
data class MusicCategoriesData(
    @field:Json(name = "music_categories") val musicCategories: List<MusicCategory>? = null,
)
