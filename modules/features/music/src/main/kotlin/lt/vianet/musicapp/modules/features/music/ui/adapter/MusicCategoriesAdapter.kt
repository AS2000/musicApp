package lt.vianet.musicapp.modules.features.music.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.features.music.databinding.ViewMusicCategoriesItemBinding
import lt.vianet.musicapp.modules.features.music.ui.view.holder.MusicCategoriesViewHolder

class MusicCategoriesAdapter(
    private val items: MutableList<MusicCategory> = mutableListOf(),
    private val onSeeAllClicked: (categoryType: CategoryType) -> Unit,
) : RecyclerView.Adapter<MusicCategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicCategoriesViewHolder =
        MusicCategoriesViewHolder(
            ViewMusicCategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(viewHolder: MusicCategoriesViewHolder, position: Int) {
        viewHolder.bind(
            musicCategory = items[position],
            onSeeAllClicked = onSeeAllClicked,
        )
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<MusicCategory>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
