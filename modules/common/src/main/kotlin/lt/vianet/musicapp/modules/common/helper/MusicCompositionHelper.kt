package lt.vianet.musicapp.modules.common.helper

private const val SECONDS_IN_MINUTE = 60
private const val SECONDS_IN_HOUR = 3600

object MusicCompositionHelper {
    fun getMelodyWeightAsString(weight: Int?): String {
        weight ?: return ""
        return "${weight}M"
    }

    fun getMelodyLengthAsString(length: Int?): String {
        length ?: return ""

        val hours = if (length > SECONDS_IN_HOUR) length / SECONDS_IN_HOUR else 0
        val withoutHours = (length - (hours * SECONDS_IN_HOUR))
        val minutes = if (withoutHours > SECONDS_IN_MINUTE) withoutHours / SECONDS_IN_MINUTE else 0
        val seconds = (withoutHours - (minutes * SECONDS_IN_MINUTE))

        var compositionLength = ""
        compositionLength += if (hours > 0) "${hours}h" else ""
        compositionLength += if (minutes > 0) " ${minutes}m" else ""
        compositionLength += if (seconds > 0) " ${seconds}s" else ""

        return compositionLength.trim()
    }

    fun concatenateStrings(value1: String?, value2: String?): String {
        var returnedString = ""
        returnedString += value1 ?: ""
        returnedString += if (!value1.isNullOrBlank() && !value2.isNullOrBlank()) " - " else ""
        returnedString += value2 ?: ""

        return returnedString
    }

    fun concatenateInts(value1: Int?, value2: Int?): String {
        var returnedString = ""
        returnedString += if (value1 != null) getMelodyWeightAsString(weight = value1) else ""
        returnedString += if (value1 != null && value2 != null) " - " else ""
        returnedString += if (value2 != null) getMelodyLengthAsString(length = value2) else ""

        return returnedString
    }
}
