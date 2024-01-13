package lt.vianet.musicapp.modules.features.playlist.ui.view.holder

import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.common.extension.gone
import lt.vianet.musicapp.modules.common.extension.visible
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getConcatenatedInts
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getConcatenatedStrings
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.features.playlist.databinding.ViewPlaylistItemBinding

class PlaylistViewHolder(private val viewBinding: ViewPlaylistItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(
        musicItem: MusicItem,
        playListScreenType: PlayListScreenType,
        onItemSaveClicked: (itemId: Int) -> Unit,
    ) {
        with(viewBinding) {
            viewPlaylistMelodyTitle.text =
                getConcatenatedStrings(value1 = musicItem.performer, value2 = musicItem.title)

            viewPlaylistMelodyWeightLength.text =
                getConcatenatedInts(value1 = musicItem.weight, value2 = musicItem.length)
        }

        setupListeners(itemId = musicItem.id, onItemSaveClicked = onItemSaveClicked)

        renderButtons(playListScreenType = playListScreenType)
    }

    private fun renderButtons(playListScreenType: PlayListScreenType) {
        when (playListScreenType) {
            PlayListScreenType.MEMORY -> buttonVisible()

            PlayListScreenType.FILE_SYSTEM -> iconVisible()

            else -> allHided()
        }
    }

    private fun buttonVisible() {
        with(viewBinding) {
            viewMemoryButtonSave.visible()
            viewMemoryCheckedIcon.gone()
        }
    }

    private fun iconVisible() {
        with(viewBinding) {
            viewMemoryButtonSave.gone()
            viewMemoryCheckedIcon.visible()
        }
    }

    private fun allHided() {
        with(viewBinding) {
            viewMemoryButtonSave.gone()
            viewMemoryCheckedIcon.gone()
        }
    }

    private fun setupListeners(itemId: Int?, onItemSaveClicked: (itemId: Int) -> Unit) {
        itemId ?: return

        with(viewBinding) {
            viewMemoryButtonSave.setOnClickListener {
                onItemSaveClicked(itemId)
            }
        }
    }
}
