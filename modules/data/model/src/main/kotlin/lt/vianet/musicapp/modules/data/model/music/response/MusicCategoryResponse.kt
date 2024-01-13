package lt.vianet.musicapp.modules.data.model.music.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lt.vianet.musicapp.modules.data.model.errorData.ErrorData
import lt.vianet.musicapp.modules.data.model.music.MusicCategory

@JsonClass(generateAdapter = true)
data class MusicCategoryResponse(
    @field:Json(name = "data") val data: MusicCategoryData? = null,
    @field:Json(name = "error_data") val errorData: ErrorData? = null,
)

@JsonClass(generateAdapter = true)
data class MusicCategoryData(
    @field:Json(name = "music_category") val musicCategory: MusicCategory? = null,
)
