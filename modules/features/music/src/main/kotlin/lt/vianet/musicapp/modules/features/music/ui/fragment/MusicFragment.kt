package lt.vianet.musicapp.modules.features.music.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import lt.vianet.musicapp.modules.features.music.R
import lt.vianet.musicapp.modules.features.music.databinding.FragmentMusicBinding

class MusicFragment : Fragment(R.layout.fragment_music) {
    private val viewBinding: FragmentMusicBinding by viewBinding(FragmentMusicBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
        setupUI()
    }

    private fun setupListeners() {}

    private fun setupObservers() {}

    private fun setupUI() {}
}
