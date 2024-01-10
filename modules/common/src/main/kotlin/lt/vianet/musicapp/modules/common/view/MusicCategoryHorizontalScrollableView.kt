package lt.vianet.musicapp.modules.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import lt.vianet.musicapp.modules.common.helper.MusicCategoryHelper.getCategoryType
import lt.vianet.musicapp.modules.common.view.adapter.MusicAdapter
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.music.MusicCategory
import lt.vienet.musicApp.modules.common.databinding.ViewMusicCategoryHorizontalScrollableBinding

class MusicCategoryHorizontalScrollableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val viewBinding =
        ViewMusicCategoryHorizontalScrollableBinding.inflate(
            LayoutInflater.from(context),
            this,
            true,
        )

    private val musicAdapter = MusicAdapter()

    init {
        setupView()
    }

    private fun setupView() {
        with(viewBinding.viewCategoryRecyclerViewMusicItemsHorizontal) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = musicAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    fun setCategory(category: MusicCategory) {
        musicAdapter.setItems(items = category.musicItems ?: emptyList())

        category.categoryType?.let { setupCategoryTitle(categoryType = it) }
    }

    private fun setupCategoryTitle(categoryType: CategoryType) {
        viewBinding.viewCategoryCategoryTitle.text =
            getCategoryType(context = context, categoryType = categoryType)
    }
}
