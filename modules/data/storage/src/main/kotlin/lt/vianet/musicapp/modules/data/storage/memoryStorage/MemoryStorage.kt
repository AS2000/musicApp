package lt.vianet.musicapp.modules.data.storage.memoryStorage

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import javax.inject.Inject

class MemoryStorage @Inject constructor(
    private val context: Context,
) {
    companion object {
        private const val MEMORY_STORAGE = "memory_storage"
        private const val KEY_MUSIC_CATEGORY = "music_category"
    }

    private var sharedPreferences: SharedPreferences? = null
    private val moshi: Moshi = Moshi.Builder().build()
    private val jsonAdapter: JsonAdapter<MusicCategory> = moshi.adapter(MusicCategory::class.java)

    init {
        createMemoryStorage()
    }

    private fun createMemoryStorage() {
        try {
            sharedPreferences = context.getSharedPreferences(MEMORY_STORAGE, Context.MODE_PRIVATE)
        } catch (exception: Exception) {
            Log.e(TAG, "Failed to create SharedPreferences Memory Storage", exception)
        }
    }

    fun setMusicCategory(musicCategory: MusicCategory?) =
        sharedPreferences?.edit()?.putString(KEY_MUSIC_CATEGORY, jsonAdapter.toJson(musicCategory))
            ?.apply()

    fun getMusicCategory(): MusicCategory? {
        val data = sharedPreferences?.getString(KEY_MUSIC_CATEGORY, null) ?: return null
        return jsonAdapter.fromJson(data)
    }

    fun updateMusicCategory(itemId: Int) {
        val musicCategory = getMusicCategory() ?: return
        val updatedMusicCategory = musicCategory.copy(
            musicItems = getUpdatedMusicItems(
                itemId = itemId,
                musicItems = musicCategory.musicItems,
            ),
        )
        setMusicCategory(musicCategory = updatedMusicCategory)
    }

    private fun getUpdatedMusicItems(itemId: Int, musicItems: List<MusicItem>?): List<MusicItem> {
        musicItems ?: return listOf()

        return musicItems.map {
            if (it.id == itemId) {
                return@map it.copy(
                    isDownloaded = true,
                )
            } else {
                return@map it
            }
        }
    }

    fun clearSharedPreferences() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}
