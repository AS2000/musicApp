package lt.vianet.musicapp.modules.data.model.errorData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorData(
    @field:Json(name = "code") val errorCode: Int? = null,
    @field:Json(name = "message") val errorMessage: String? = null,
)
