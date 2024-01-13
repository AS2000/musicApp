package lt.vianet.musicapp.modules.data.network.service

import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoriesResponse
import lt.vianet.musicapp.modules.data.model.music.response.MusicCategoryResponse
import lt.vianet.musicapp.modules.data.network.ApiEndpoint
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MusicService {
    @GET(ApiEndpoint.musicCategories)
    suspend fun getMusicCategories(): MusicCategoriesResponse?

    @GET(ApiEndpoint.musicCategory)
    suspend fun getMusicCategory(
        @QueryMap(encoded = true) queryMap: Map<String, String?>? = emptyMap(),
    ): MusicCategoryResponse?
}
