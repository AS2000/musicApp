package lt.vianet.musicapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lt.vianet.musicapp.modules.common.navigator.ModuleNavigatorInterface
import lt.vianet.musicapp.navigator.ModuleNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun providesModuleNavigator(): ModuleNavigatorInterface = ModuleNavigator()
}
