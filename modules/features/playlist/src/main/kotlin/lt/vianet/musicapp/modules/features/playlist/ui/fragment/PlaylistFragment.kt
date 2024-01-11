package lt.vianet.musicapp.modules.features.playlist.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import lt.vianet.musicapp.modules.common.constant.Navigation
import lt.vianet.musicapp.modules.common.extension.TAG
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.features.playlist.R
import lt.vianet.musicapp.modules.features.playlist.databinding.FragmentPlaylistBinding

@AndroidEntryPoint
class PlaylistFragment : Fragment(R.layout.fragment_playlist) {
    private val viewBinding: FragmentPlaylistBinding by viewBinding(FragmentPlaylistBinding::bind)
    private var playListScreenType: PlayListScreenType = PlayListScreenType.MEMORY

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFragmentArgs()
        setupListeners()
        setupObservers()
        setupUI()
    }

    private fun getFragmentArgs() {
        playListScreenType = getPlayListScreenTypeArgumentValue() ?: PlayListScreenType.MEMORY
        Log.e(TAG, "getFragmentArgs - playListScreenType: $playListScreenType")
    }

    private fun setupListeners() {}

    private fun setupObservers() {}

    private fun setupUI() {}

    @Suppress("DEPRECATION")
    private fun getPlayListScreenTypeArgumentValue(): PlayListScreenType? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(Navigation.SCREEN_TYPE, PlayListScreenType::class.java)
        } else {
            arguments?.getSerializable(Navigation.SCREEN_TYPE) as PlayListScreenType?
        }
    }
}
