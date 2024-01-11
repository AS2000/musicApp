package lt.vianet.musicapp.modules.features.music.ui.view.holder

import androidx.recyclerview.widget.RecyclerView
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vianet.musicapp.modules.features.music.databinding.ViewMusicCategoriesItemBinding

class MusicCategoriesViewHolder(private val viewBinding: ViewMusicCategoriesItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(musicCategory: MusicCategory, onSeeAllClicked: (categoryType: CategoryType) -> Unit) {
        with(viewBinding.scrollableViewMusicCategory) {
            setMusicCategory(musicCategory = musicCategory)
            setupOnSeeAllClicked(onSeeAllClicked = onSeeAllClicked)
        }
    }
}
