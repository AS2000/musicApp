package lt.vianet.musicapp.modules.data.model.music

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusicItem(
    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "performer")
    val performer: String? = null,

    @field:Json(name = "title")
    val title: String? = null,

    @field:Json(name = "weight")
    val weight: Int? = null,

    @field:Json(name = "length")
    val length: Int? = null,

    @field:Json(name = "image_url")
    val imageUrl: String? = null,
)
