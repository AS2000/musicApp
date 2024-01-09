package lt.vianet.musicapp.modules.data.model.music

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Music(
    @field:Json(name = "id")
    val id: Int? = null,
)
