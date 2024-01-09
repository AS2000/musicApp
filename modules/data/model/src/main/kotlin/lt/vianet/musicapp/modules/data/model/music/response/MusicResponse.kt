package lt.vianet.musicapp.modules.data.model.music.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lt.vianet.musicapp.modules.data.model.errorData.ErrorData
import lt.vianet.musicapp.modules.data.model.music.Music

@JsonClass(generateAdapter = true)
data class MusicResponse(
    @field:Json(name = "data") val data: MusicData? = null,
    @field:Json(name = "error_data") val errorData: ErrorData? = null,
)

@JsonClass(generateAdapter = true)
data class MusicData(
    @field:Json(name = "community") val community: Music? = null,
)
