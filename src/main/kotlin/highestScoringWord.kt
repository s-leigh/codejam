package codejam

import powerSet
import toUpperCase
import withScores

val LETTER_SCORES = mapOf(
    'A' to 1, 'E' to 1, 'I' to 1, 'O' to 1, 'U' to 1, 'L' to 1, 'N' to 1, 'R' to 1, 'S' to 1, 'T' to 1,
    'D' to 2, 'G' to 2,
    'B' to 3, 'C' to 3, 'M' to 3, 'P' to 3,
    'F' to 4, 'H' to 4, 'V' to 4, 'W' to 4, 'Y' to 4,
    'K' to 5,
    'J' to 8, 'X' to 8,
    'Q' to 10, 'Z' to 10
)

fun highestScoringWord(letters: CharArray, dictionary: List<String>): Pair<String, Int>? {
    val possibleWords = dictionary
        .map { it.toCharArray().toUpperCase() }
        .filter { possibleWordLetters -> possibleWordLetters.all { it in letters.toUpperCase() } }
        .withScores()
    return possibleWords.maxByOrNull { it.second }?.run {
        Pair(first.joinToString(""), second)
    }
}

fun highestScoringWordWithPreprocessedDictionary(
    letters: CharArray,
    dictionary: Map<Int, List<CharArray>>
): Pair<String, Int>? {
    val values = letters.toUpperCase().map { LETTER_SCORES[it]!! }
    val possibleWordScores = values.powerSet()
    val possibleWords = possibleWordScores.flatMap { dictionary[it] ?: listOf() }
    return possibleWords
        .map(CharArray::toUpperCase)
        .filter { possibleWordLetters -> possibleWordLetters.all { it in letters.toUpperCase() } }
        .withScores()
        .maxByOrNull { it.second }?.run {
            Pair(first.joinToString(""), second)
        }
}
