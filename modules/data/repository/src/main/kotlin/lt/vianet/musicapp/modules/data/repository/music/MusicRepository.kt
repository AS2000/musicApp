package lt.vianet.musicapp.modules.data.repository.music

import android.content.Context
import android.util.Log
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.data.model.music.response.MusicResponse
import lt.vianet.musicapp.modules.data.network.service.MusicService
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val context: Context,
    private val musicService: MusicService,
) {
    suspend fun getMusic(queryMap: Map<String, String?>? = null): MusicResponse? {
        try {
            return musicService.getMusic(queryMap)
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
        return null
    }
}
