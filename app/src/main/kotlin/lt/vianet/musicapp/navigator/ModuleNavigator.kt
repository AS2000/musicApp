package lt.vianet.musicapp.navigator

import androidx.navigation.NavController
import lt.vianet.musicapp.modules.common.navigator.ModuleNavigatorInterface
import javax.inject.Inject

class ModuleNavigator @Inject constructor() : ModuleNavigatorInterface {
    override fun navigateToMusic(navController: NavController) {
        navController.navigate(lt.vianet.musicapp.modules.features.music.R.id.music_navigation_graph)
    }
}
