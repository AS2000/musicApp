package lt.vianet.musicapp.modules.features.playlist.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import lt.vianet.musicapp.modules.features.playlist.R
import lt.vianet.musicapp.modules.features.playlist.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment(R.layout.fragment_playlist) {
    private val viewBinding: FragmentPlaylistBinding by viewBinding(FragmentPlaylistBinding::bind)

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
