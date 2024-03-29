package lt.vianet.musicapp.modules.data.storage.database.music.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import lt.vianet.musicapp.modules.data.storage.database.music.entities.MusicItemEntity

@Dao
interface MusicItemDao {
    @Query("SELECT * FROM music")
    suspend fun getMusicItemEntities(): List<MusicItemEntity>

    @Insert(onConflict = REPLACE)
    suspend fun addMusicItemEntities(musicItemEntities: List<MusicItemEntity>)

    @Query("DELETE FROM music")
    suspend fun delete()

    @Transaction
    suspend fun setMusicItems(musicItemEntities: List<MusicItemEntity>) {
        delete()
        addMusicItemEntities(musicItemEntities)
    }

    @Query("SELECT length FROM music WHERE is_downloaded = 1")
    suspend fun getDownloadedItemsLengths(): List<Int>

    @Query("UPDATE music SET is_downloaded = 1 WHERE itemId = :itemId")
    suspend fun markAsDownloadedMusicItem(itemId: Int)

    @Query("SELECT count(*) FROM music")
    suspend fun checkHasDatabaseData(): Int
}
