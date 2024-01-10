package lt.vianet.musicapp.modules.common.view.holder

import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vienet.musicApp.modules.common.databinding.ViewMusicItemBinding

class MusicViewHolder(private val viewBinding: ViewMusicItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(musicItem: MusicItem) {
        viewBinding.viewMusicComposition.setMusicItem(musicItem = musicItem)
    }
}
