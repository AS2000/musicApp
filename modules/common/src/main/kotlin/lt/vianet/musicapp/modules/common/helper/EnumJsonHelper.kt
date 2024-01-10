package lt.vianet.musicapp.modules.common.helper

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import lt.vianet.musicapp.modules.data.model.enums.EnumValueInterface

object EnumJsonHelper {
    inline fun <reified T> createEnumJsonAdapter(): JsonAdapter<T> where T : Enum<T>, T : EnumValueInterface {
        return object : JsonAdapter<T>() {
            @FromJson
            override fun fromJson(reader: JsonReader): T? {
                return if (reader.peek() != JsonReader.Token.NULL) {
                    val value = reader.nextString()
                    enumValues<T>().firstOrNull { it.value == value }
                } else {
                    reader.nextNull()
                }
            }

            @ToJson
            override fun toJson(writer: JsonWriter, value: T?) {
                writer.value(value?.value)
            }
        }
    }
}
