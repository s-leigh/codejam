package codejam

import powerSet
import withScores

val LETTER_SCORES =
    "aeioulnrst".associate { it to 1 } +
            "dg".associate { it to 2 } +
            "bcmp".associate { it to 3 } +
            "fhvwy".associate { it to 4 } +
            "k".associate { it to 5 } +
            "jx".associate { it to 8 } +
            "qz".associate { it to 10 }

fun highestScoringWord(letters: CharArray, dictionary: List<String>): Pair<String, Int>? {
    val possibleWords = dictionary
        .map { it.toCharArray() }
        .filter { possibleWordLetters -> possibleWordLetters.all { it in letters } }
        .withScores()
    return possibleWords.maxByOrNull { it.second }?.run {
        Pair(first.joinToString(""), second)
    }
}

fun highestScoringWordWithPreprocessedDictionary(
    letters: CharArray,
    dictionary: Map<Int, List<CharArray>>
): Pair<String, Int>? {
    val values = letters.map { LETTER_SCORES[it]!! }
    val possibleWordScores = values.powerSet()
    val possibleWords = possibleWordScores.flatMap { dictionary[it] ?: listOf() }
    return possibleWords
        .filter { possibleWordLetters -> possibleWordLetters.all { it in letters } }
        .withScores()
        .maxByOrNull { it.second }?.run {
            Pair(first.joinToString(""), second)
        }
}
