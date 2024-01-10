package lt.vianet.musicapp.modules.data.model.music.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lt.vianet.musicapp.modules.data.model.errorData.ErrorData
import lt.vianet.musicapp.modules.data.model.music.MusicItem

@JsonClass(generateAdapter = true)
data class MusicItemsResponse(
    @field:Json(name = "data") val data: MusicItemsData? = null,
    @field:Json(name = "error_data") val errorData: ErrorData? = null,
)

@JsonClass(generateAdapter = true)
data class MusicItemsData(
    @field:Json(name = "music_items") val musicItems: List<MusicItem>? = null,
)
