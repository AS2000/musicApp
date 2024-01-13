package lt.vianet.musicapp.modules.features.playlist.ui.view.holder

import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.concatenateInts
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.concatenateStrings
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.features.playlist.databinding.ViewPlaylistItemBinding

class PlaylistViewHolder(private val viewBinding: ViewPlaylistItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(musicItem: MusicItem, onItemSaveClicked: (itemId: Int) -> Unit) {
        with(viewBinding) {
            viewPlaylistMelodyTitle.text =
                concatenateStrings(value1 = musicItem.performer, value2 = musicItem.title)

            viewPlaylistMelodyWeightLength.text =
                concatenateInts(value1 = musicItem.weight, value2 = musicItem.length)
        }

        setupListeners(itemId = musicItem.id, onItemSaveClicked = onItemSaveClicked)
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
