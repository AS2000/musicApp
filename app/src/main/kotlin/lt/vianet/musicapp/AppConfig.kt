package lt.vianet.musicapp

import lt.vianet.musicapp.modules.common.config.AppConfigInterface

object AppConfig : AppConfigInterface {
    override val urlApi: String
        get() = BuildConfig.URL_API
}
