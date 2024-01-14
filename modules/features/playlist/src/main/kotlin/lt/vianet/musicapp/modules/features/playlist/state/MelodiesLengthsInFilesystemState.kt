package lt.vianet.musicapp.modules.features.playlist.state

sealed class MelodiesLengthsInFilesystemState {
    data object Initial : MelodiesLengthsInFilesystemState()
    data object Loading : MelodiesLengthsInFilesystemState()
    data class Success(val melodiesLengths: List<Int>?) : MelodiesLengthsInFilesystemState()
    data object Error : MelodiesLengthsInFilesystemState()
}
