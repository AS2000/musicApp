package lt.vianet.musicapp.modules.data.storage.database.music.mappers

import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.data.storage.database.music.entities.MusicItemEntity

object MusicItemEntityMapper {

    fun mapToMusicItem(musicItemEntities: List<MusicItemEntity>): List<MusicItem> =
        musicItemEntities.map { musicItemEntity ->
            MusicItem(
                id = musicItemEntity.itemId,
                performer = musicItemEntity.performer,
                title = musicItemEntity.title,
                weight = musicItemEntity.weight,
                length = musicItemEntity.length,
                imageName = musicItemEntity.imageName,
                fileName = musicItemEntity.fileName,
                isDownloaded = musicItemEntity.isDownloaded,
            )
        }

    fun mapToMusicItemEntities(musicItems: List<MusicItem>): List<MusicItemEntity> =
        musicItems.map { musicItem ->
            MusicItemEntity(
                itemId = musicItem.id,
                performer = musicItem.performer,
                title = musicItem.title,
                weight = musicItem.weight,
                length = musicItem.length,
                imageName = musicItem.imageName,
                fileName = musicItem.fileName,
                isDownloaded = musicItem.isDownloaded,
            )
        }
}
