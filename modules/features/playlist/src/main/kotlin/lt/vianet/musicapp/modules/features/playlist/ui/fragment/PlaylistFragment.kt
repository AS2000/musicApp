package lt.vianet.musicapp.modules.features.playlist.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import lt.vianet.musicapp.modules.common.constant.Navigation
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import lt.vianet.musicapp.modules.features.playlist.R
import lt.vianet.musicapp.modules.features.playlist.databinding.FragmentPlaylistBinding

@AndroidEntryPoint
class PlaylistFragment : Fragment(R.layout.fragment_playlist) {
    private val viewBinding: FragmentPlaylistBinding by viewBinding(FragmentPlaylistBinding::bind)

    private var playListScreenType: PlayListScreenType = PlayListScreenType.MEMORY
    private var categoryType: CategoryType = CategoryType.ROCK

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFragmentArgs()
        setupListeners()
        setupObservers()
        setupUI()
    }

    @Suppress("DEPRECATION")
    private fun getFragmentArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            playListScreenType =
                arguments?.getSerializable(Navigation.SCREEN_TYPE, PlayListScreenType::class.java)
                    ?: PlayListScreenType.MEMORY
            categoryType =
                arguments?.getSerializable(Navigation.CATEGORY_TYPE, CategoryType::class.java)
                    ?: CategoryType.ROCK
        } else {
            playListScreenType =
                arguments?.getSerializable(Navigation.SCREEN_TYPE) as PlayListScreenType?
                    ?: PlayListScreenType.MEMORY
            categoryType = arguments?.getSerializable(Navigation.CATEGORY_TYPE) as CategoryType?
                ?: CategoryType.ROCK
        }
    }

    private fun setupListeners() {}

    private fun setupObservers() {}

    private fun setupUI() {}
}
