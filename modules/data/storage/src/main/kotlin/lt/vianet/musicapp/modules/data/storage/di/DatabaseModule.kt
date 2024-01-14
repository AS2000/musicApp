package lt.vianet.musicapp.modules.data.storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lt.vianet.musicapp.modules.data.storage.database.music.MusicDatabase
import lt.vianet.musicapp.modules.data.storage.database.music.dao.MusicItemDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMusicDatabase(@ApplicationContext context: Context): MusicDatabase =
        Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            MusicDatabase.DATABASE_NAME,
        )
            .build()

    @Provides
    @Singleton
    fun provideMusicItemDao(musicDatabase: MusicDatabase): MusicItemDao =
        musicDatabase.getMusicItemDao()
}
