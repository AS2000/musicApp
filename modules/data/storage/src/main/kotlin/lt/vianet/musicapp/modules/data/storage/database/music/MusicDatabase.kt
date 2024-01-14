package lt.vianet.musicapp.modules.data.storage.database.music

import androidx.room.Database
import androidx.room.RoomDatabase
import lt.vianet.musicapp.modules.data.storage.database.music.dao.MusicItemDao
import lt.vianet.musicapp.modules.data.storage.database.music.entities.MusicItemEntity

@Database(version = 1, entities = [MusicItemEntity::class])
abstract class MusicDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "music_db.db"
    }

    abstract fun getMusicItemDao(): MusicItemDao
}
