package lt.vianet.musicapp.modules.data.network.service

import lt.vianet.musicapp.modules.data.model.music.response.MusicResponse
import lt.vianet.musicapp.modules.data.network.ApiEndpoint
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MusicService {
    @GET(ApiEndpoint.music)
    suspend fun getMusic(
        @QueryMap(encoded = true) queryMap: Map<String, String?>? = emptyMap(),
    ): MusicResponse?
}
