package lt.vianet.musicapp.modules.features.playlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vianet.musicapp.modules.features.playlist.databinding.ViewPlaylistItemBinding
import lt.vianet.musicapp.modules.features.playlist.ui.view.holder.PlaylistViewHolder

class PlaylistAdapter(
    private val items: MutableList<MusicItem> = mutableListOf(),
    private val onItemSaveClicked: (itemId: Int) -> Unit,
) : RecyclerView.Adapter<PlaylistViewHolder>() {

    private var playListScreenType: PlayListScreenType = PlayListScreenType.PLAYLIST

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(
            ViewPlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(viewHolder: PlaylistViewHolder, position: Int) {
        viewHolder.bind(
            musicItem = items[position],
            playListScreenType = playListScreenType,
            onItemSaveClicked = onItemSaveClicked,
        )
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<MusicItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setPlayListScreenType(playListScreenType: PlayListScreenType) {
        this.playListScreenType = playListScreenType
    }
}
