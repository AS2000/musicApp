package lt.vianet.musicapp.modules.common.helper

import android.content.Context
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vienet.musicApp.modules.common.R

object MusicCategoryHelper {
    fun getCategoryType(context: Context, categoryType: CategoryType): String =
        when (categoryType) {
            CategoryType.ROCK -> context.resources.getString(
                R.string.music_category_type_rock,
            )

            CategoryType.BLUES -> context.resources.getString(
                R.string.music_category_type_blues,
            )

            CategoryType.JAZZ -> context.resources.getString(
                R.string.music_category_type_jazz,
            )

            else -> ""
        }
}
