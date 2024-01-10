package lt.vianet.musicapp.modules.common.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.common.view.holder.MusicViewHolder
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vienet.musicApp.modules.common.databinding.ViewMusicItemBinding

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

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<MusicItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
