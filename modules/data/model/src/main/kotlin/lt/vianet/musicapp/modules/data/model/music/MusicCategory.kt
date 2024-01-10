package lt.vianet.musicapp.modules.data.model.music

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import lt.vianet.musicapp.modules.data.model.enums.CategoryType

@JsonClass(generateAdapter = true)
data class MusicCategory(
    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "category_type")
    val categoryType: CategoryType? = null,

    @field:Json(name = "music_items")
    val musicItems: List<MusicItem>? = null,
)
