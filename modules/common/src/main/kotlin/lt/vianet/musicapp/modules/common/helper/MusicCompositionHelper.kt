package lt.vianet.musicapp.modules.common.helper

object MusicCompositionHelper {
    fun getWeightAsString(weight: Int?): String {
        weight ?: return ""
        return "${weight}M"
    }

    fun getLengthAsString(length: Int?): String {
        length ?: return ""

        val hours = if (length > 3600) length / 3600 else 0
        val withoutHours = (length - (hours * 3600))
        val minutes = if (withoutHours > 60) withoutHours / 60 else 0
        val seconds = (withoutHours - (minutes * 60))

        var compositionLength = ""
        compositionLength += if (hours > 0) "${hours}h" else ""
        compositionLength += if (minutes > 0) " ${minutes}m" else ""
        compositionLength += if (seconds > 0) " ${seconds}s" else ""

        return compositionLength
    }
}
