package lt.vianet.musicapp.modules.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import lt.vianet.musicapp.modules.common.databinding.ViewMusicCompositionBinding
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getMelodyLengthAsString
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getMelodyWeightAsString
import lt.vianet.musicapp.modules.data.model.music.MusicItem
import javax.inject.Inject

const val URL_IMAGES = "http://music-app-mock.vianet.lt/images/"

@AndroidEntryPoint
class MusicCompositionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    @Inject
    lateinit var glideRequestManager: RequestManager

    private val viewBinding =
        ViewMusicCompositionBinding.inflate(LayoutInflater.from(context), this, true)

    fun setMusicItem(musicItem: MusicItem) {
        displayImage(imageName = musicItem.imageName)

        with(viewBinding) {
            viewMusicItemPerformer.text = musicItem.performer ?: ""
            viewMusicItemMelodyTitle.text = musicItem.title ?: ""
            viewMusicItemWeight.text = getMelodyWeightAsString(weight = musicItem.weight)
            viewMusicItemLength.text = getMelodyLengthAsString(length = musicItem.length)
        }
    }

    private fun displayImage(imageName: String?) {
        imageName ?: return

        val imagerFullUrl = getImageFullUrl(imageName = imageName)

        glideRequestManager.load(imagerFullUrl).into(viewBinding.viewMusicItemImage)
    }

    private fun getImageFullUrl(imageName: String): String =
        "$URL_IMAGES$imageName"
}
