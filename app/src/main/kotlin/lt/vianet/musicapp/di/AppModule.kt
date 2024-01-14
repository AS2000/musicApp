package lt.vianet.musicapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lt.vianet.musicapp.Config
import lt.vianet.musicapp.modules.data.network.service.MusicService
import lt.vianet.musicapp.modules.data.repository.music.MusicRepository
import lt.vianet.musicapp.modules.data.storage.memoryStorage.MemoryStorage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ------------------ Retrofit ------------------
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Config.urlApi)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

    // ------------------ Services ------------------
    @Provides
    @Singleton
    internal fun provideMusicService(retrofit: Retrofit): MusicService =
        retrofit.create(MusicService::class.java)

    // ------------------ Repositories ------------------
    @Provides
    @Singleton
    fun providesMusicRepository(
        @ApplicationContext context: Context,
        musicService: MusicService,
    ) = MusicRepository(context = context, musicService = musicService)

    // ------------------ Shared Preferences ------------------
    @Provides
    @Singleton
    fun providesMemoryStorage(
        @ApplicationContext context: Context,
    ) = MemoryStorage(context = context)
}
