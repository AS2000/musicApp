package lt.vianet.musicapp.modules.common.view.holder

import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.common.databinding.ViewMusicItemBinding
import lt.vianet.musicapp.modules.data.model.music.MusicItem

class MusicViewHolder(private val viewBinding: ViewMusicItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(musicItem: MusicItem) {
        viewBinding.viewMusicComposition.setMusicItem(musicItem = musicItem)
    }
}
