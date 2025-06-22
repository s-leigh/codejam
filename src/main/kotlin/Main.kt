package codejam

import java.util.Locale.getDefault

const val DICTIONARY_FILENAME = "dictionary.txt"

val LETTER_SCORES = mapOf(
    'A' to 1, 'E' to 1, 'I' to 1, 'O' to 1, 'U' to 1, 'L' to 1, 'N' to 1, 'R' to 1, 'S' to 1, 'T' to 1,
    'D' to 2, 'G' to 2,
    'B' to 3, 'C' to 3, 'M' to 3, 'P' to 3,
    'F' to 4, 'H' to 4, 'V' to 4, 'W' to 4, 'Y' to 4,
    'K' to 5,
    'J' to 8, 'X' to 8,
    'Q' to 10, 'Z' to 10
)

//val dictionary = object {}.javaClass.getResource(DICTIONARY_FILENAME)?.readText()
//    ?: throw IllegalArgumentException("Dictionary file not found: $DICTIONARY_FILENAME")

fun highestScoringWord(letters: CharArray, dictionary: List<String>): Pair<String, Int>? {
    val possibleWords = dictionary
        .map { it.uppercase(getDefault()) }
        .map { it.toCharArray() }
        .filter { possibleWordLetters -> possibleWordLetters.all { it in letters } }
    val possibleWordsWithScores = possibleWords.map { it to it.sumOf { LETTER_SCORES[it]!! } }
    return possibleWordsWithScores.maxByOrNull { it.second }?.run {
        Pair(first.joinToString(""), second)
    }
}