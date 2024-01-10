package lt.vianet.musicapp.modules.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import lt.vienet.musicApp.modules.common.databinding.ViewMusicCompositionBinding

class MusicCompositionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val viewBinding =
        ViewMusicCompositionBinding.inflate(
            LayoutInflater.from(context),
            this,
            true,
        )

    fun setMusicItem(musicItem: MusicItem) {
        setImage(imageUrl = musicItem.imageUrl)

        with(viewBinding) {
            viewMusicItemPerformer.text = musicItem.performer ?: ""
            viewMusicItemMelodyTitle.text = musicItem.title ?: ""
            viewMusicItemWeight.text = musicItem.weight.toString()
            viewMusicItemLength.text = musicItem.length.toString()
        }
    }

    private fun setImage(imageUrl: String?) {
    }
}
