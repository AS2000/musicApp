package lt.vianet.musicapp.modules.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vienet.musicApp.modules.common.R
import lt.vienet.musicApp.modules.common.databinding.ViewMemoryUsageBinding

class MemoryUsageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val viewBinding =
        ViewMemoryUsageBinding.inflate(LayoutInflater.from(context), this, true)
    private var screenType: PlayListScreenType? = null
    private var melodyLength: Int = 0

    init {
        resolveAttributes(attrs = attrs)
        setupView()
    }

    private fun resolveAttributes(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MemoryUsageView)

        try {
            val viewTypeIndex = typedArray.getInt(R.styleable.MemoryUsageView_muv_view_type, 0)

            when (viewTypeIndex) {
                0 -> screenType = PlayListScreenType.MEMORY
                1 -> screenType = PlayListScreenType.FILE_SYSTEM
            }
        } finally {
            typedArray.recycle()
        }
    }

    private fun setupView() {
        screenType ?: return

        when (screenType) {
            PlayListScreenType.MEMORY -> renderMemoryView()
            PlayListScreenType.FILE_SYSTEM -> renderFileSystemView()
            else -> {}
        }
    }

    private fun renderMemoryView() {
        with(viewBinding) {
            viewMemoryTitle.text = context.resources.getString(
                R.string.memory_usage_view_memory,
            )
        }
    }

    private fun renderFileSystemView() {
        with(viewBinding) {
            viewMemoryTitle.text = context.resources.getString(
                R.string.memory_usage_view_file_system,
            )
        }
    }

    fun setMelodyLength(melodyLength: Int) {
        viewBinding.viewMemoryMelodyLength.text =
            MusicCompositionHelper.getMelodyLengthAsString(length = melodyLength)
    }
}
