package lt.vianet.musicapp.modules.common.helper

private const val SECONDS_IN_MINUTE = 60
private const val SECONDS_IN_HOUR = 3600

object MusicCompositionHelper {
    fun getWeightAsString(weight: Int?): String {
        weight ?: return ""
        return "${weight}M"
    }

    fun getLengthAsString(length: Int?): String {
        length ?: return ""

        val hours = if (length > SECONDS_IN_HOUR) length / SECONDS_IN_HOUR else 0
        val withoutHours = (length - (hours * SECONDS_IN_HOUR))
        val minutes = if (withoutHours > SECONDS_IN_MINUTE) withoutHours / SECONDS_IN_MINUTE else 0
        val seconds = (withoutHours - (minutes * SECONDS_IN_MINUTE))

        var compositionLength = ""
        compositionLength += if (hours > 0) "${hours}h" else ""
        compositionLength += if (minutes > 0) " ${minutes}m" else ""
        compositionLength += if (seconds > 0) " ${seconds}s" else ""

        return compositionLength
    }
}
