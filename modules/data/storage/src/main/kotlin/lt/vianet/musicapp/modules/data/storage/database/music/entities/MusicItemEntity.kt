package lt.vianet.musicapp.modules.data.storage.database.music.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class MusicItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "itemId") val itemId: Int? = null,
    @ColumnInfo(name = "performer") val performer: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "weight") val weight: Int? = null,
    @ColumnInfo(name = "length") val length: Int? = null,
    @ColumnInfo(name = "image_name") val imageName: String? = null,
    @ColumnInfo(name = "file_name") val fileName: String? = null,
    @ColumnInfo(name = "is_downloaded") val isDownloaded: Boolean? = null,
)
