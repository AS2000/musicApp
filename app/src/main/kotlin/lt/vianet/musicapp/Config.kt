package lt.vianet.musicapp

import lt.vianet.musicapp.modules.common.config.ConfigInterface

object Config : ConfigInterface {
    override val urlApi: String
        get() = BuildConfig.URL_API
}
