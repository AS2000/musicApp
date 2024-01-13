package lt.vianet.musicapp.modules.common.helper

import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getConcatenatedInts
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getConcatenatedStrings
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getMelodyLengthAsString
import lt.vianet.musicapp.modules.common.helper.MusicCompositionHelper.getMelodyWeightAsString
import org.junit.Test
import kotlin.test.DefaultAsserter

class MusicCompositionHelperTest {
    /** getMelodyWeightAsString() */
    @Test
    fun getMelodyWeightAsString_passed_givenNullReturnsEmptyString() {
        // Arrange
        val weight = null
        val expected = ""
        // Act
        val result = getMelodyWeightAsString(weight = weight)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected empty string!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyWeightAsString_passed_givenValueWithMinusReturnsEmptyString() {
        // Arrange
        val weight = -1
        val expected = ""
        // Act
        val result = getMelodyWeightAsString(weight = weight)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected empty string!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyWeightAsString_passed_givenValueReturnsString() {
        // Arrange
        val weight = 10
        val expected = "10M"
        // Act
        val result = getMelodyWeightAsString(weight = weight)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 10M!",
            expected = expected,
            actual = result,
        )
    }

    /** getMelodyLengthAsString() */
    @Test
    fun getMelodyLengthAsString_passed_givenNullReturnsEmptyString() {
        // Arrange
        val length = null
        val expected = ""
        // Act
        val result = getMelodyLengthAsString(length = length)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected empty string!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyLengthAsString_passed_givenValueWithMinusReturnsEmptyString() {
        // Arrange
        val length = -1
        val expected = ""
        // Act
        val result = getMelodyLengthAsString(length = length)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected empty string!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyLengthAsString_passed_givenValueReturnsString() {
        // Arrange
        val length = 641230
        val expected = "178h 7m 10s"
        // Act
        val result = getMelodyLengthAsString(length = length)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 178h 7m 10s!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyLengthAsString_passed_givenValueReturnsStringWithoutMinutes() {
        // Arrange
        val length = 640810
        val expected = "178h 10s"
        // Act
        val result = getMelodyLengthAsString(length = length)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 178h 10s!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyLengthAsString_passed_givenValueReturnsStringWithoutMinutesAndSeconds() {
        // Arrange
        val length = 640800
        val expected = "178h"
        // Act
        val result = getMelodyLengthAsString(length = length)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 178h!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getMelodyLengthAsString_passed_givenValueReturnsStringWithoutHoursAndSeconds() {
        // Arrange
        val length = 3600
        val expected = "60m"
        // Act
        val result = getMelodyLengthAsString(length = length)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 60m!",
            expected = expected,
            actual = result,
        )
    }

    /** getConcatenatedStrings() */
    @Test
    fun getConcatenatedStrings_passed_givenNullReturnsEmptyString() {
        // Arrange
        val value1 = null
        val value2 = null
        val expected = ""
        // Act
        val result = getConcatenatedStrings(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected empty string!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedStrings_passed_givenFirstNullSecondValueReturnsSecondValueAsString() {
        // Arrange
        val value1 = null
        val value2 = "Cruise"
        val expected = "Cruise"
        // Act
        val result = getConcatenatedStrings(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: Cruise!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedStrings_passed_givenFirstNullSecondValueWithSpacesReturnsSecondValueAsString() {
        // Arrange
        val value1 = null
        val value2 = " Cruise "
        val expected = "Cruise"
        // Act
        val result = getConcatenatedStrings(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: Cruise!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedStrings_passed_givenFirstValueSecondNullReturnsSecondValueAsString() {
        // Arrange
        val value1 = "Tom"
        val value2 = null
        val expected = "Tom"
        // Act
        val result = getConcatenatedStrings(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: Tom!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedStrings_passed_givenFirstValueWithSpacesSecondNullReturnsSecondValueAsString() {
        // Arrange
        val value1 = " Tom "
        val value2 = null
        val expected = "Tom"
        // Act
        val result = getConcatenatedStrings(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: Tom!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedStrings_passed_givenTwoValuesReturnsString() {
        // Arrange
        val value1 = "Tom"
        val value2 = "Cruise"
        val expected = "Tom - Cruise"
        // Act
        val result = getConcatenatedStrings(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: Tom - Cruise!",
            expected = expected,
            actual = result,
        )
    }

    /** getConcatenatedInts() */
    @Test
    fun getConcatenatedInts_passed_givenNullReturnsEmptyString() {
        // Arrange
        val value1 = null
        val value2 = null
        val expected = ""
        // Act
        val result = getConcatenatedInts(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected empty string!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedInts_passed_givenFirstNullSecondValueReturnsSecondValueAsString() {
        // Arrange
        val value1 = null
        val value2 = 3600
        val expected = "60m"
        // Act
        val result = getConcatenatedInts(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 60m!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedInts_passed_givenFirstValueSecondNullReturnsSecondValueAsString() {
        // Arrange
        val value1 = 25
        val value2 = null
        val expected = "25M"
        // Act
        val result = getConcatenatedInts(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 25M!",
            expected = expected,
            actual = result,
        )
    }

    @Test
    fun getConcatenatedInts_passed_givenTwoValuesReturnsString() {
        // Arrange
        val value1 = 25
        val value2 = 2601
        val expected = "25M - 43m 21s"
        // Act
        val result = getConcatenatedInts(value1 = value1, value2 = value2)
        // Assert
        DefaultAsserter.assertEquals(
            message = "Failed - Expected: 25M - 43m 21s!",
            expected = expected,
            actual = result,
        )
    }
}
