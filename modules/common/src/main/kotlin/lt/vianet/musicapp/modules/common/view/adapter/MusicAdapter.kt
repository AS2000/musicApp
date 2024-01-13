package lt.vianet.musicapp.modules.common.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.common.view.holder.MusicViewHolder
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vienet.musicapp.modules.common.databinding.ViewMusicItemBinding

private const val MAX_ITEMS_IN_SCROLL = 5

class MusicAdapter(
    private val items: MutableList<MusicItem> = mutableListOf(),
) : RecyclerView.Adapter<MusicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder(
            ViewMusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(viewHolder: MusicViewHolder, position: Int) {
        viewHolder.bind(
            musicItem = items[position],
        )
    }

    override fun getItemCount(): Int =
        if (items.size > MAX_ITEMS_IN_SCROLL) MAX_ITEMS_IN_SCROLL else items.size

    fun setItems(items: List<MusicItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
