package lt.vianet.musicapp.modules.common.navigator

import androidx.navigation.NavController
import lt.vianet.musicapp.modules.data.model.enums.CategoryType
import lt.vianet.musicapp.modules.data.model.enums.PlayListScreenType

interface ModuleNavigatorInterface {
    fun navigateToMusic(navController: NavController)

    fun navigateToPlaylist(
        navController: NavController,
        playListScreenType: PlayListScreenType,
        categoryType: CategoryType?,
    )
}
