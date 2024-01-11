package lt.vianet.musicapp.navigator

import android.os.Bundle
import androidx.navigation.NavController
import lt.vianet.musicapp.modules.common.constant.Navigation.SCREEN_TYPE
import lt.vianet.musicapp.modules.common.navigator.ModuleNavigatorInterface
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType
import javax.inject.Inject

class ModuleNavigator @Inject constructor() : ModuleNavigatorInterface {
    override fun navigateToMusic(navController: NavController) {
        navController.navigate(lt.vianet.musicapp.modules.features.music.R.id.music_navigation_graph)
    }

    override fun navigateToPlaylist(
        navController: NavController,
        playListScreenType: PlayListScreenType,
    ) {
        val bundle = Bundle()
        bundle.putSerializable(SCREEN_TYPE, playListScreenType)
        navController.navigate(
            lt.vianet.musicapp.modules.features.playlist.R.id.playlist_navigation_graph,
            bundle,
        )
    }
}
