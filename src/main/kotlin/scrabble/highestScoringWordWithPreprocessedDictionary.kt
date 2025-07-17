package scrabble

fun highestScoringWordWithPreprocessedDictionary(
    letters: CharArray,
    dictionary: Map<Int, List<CharArray>>
): Pair<String, Int>? {
    val values = letters.map { LETTER_SCORES[it]!! }
    val possibleWordScores = values.powerSet().sortedDescending()
    val possibleWords = possibleWordScores.flatMap { dictionary[it] ?: listOf() }
    val match = possibleWords.firstOrNull { wordMatch(letters, it) }
    if (match == null || match.isEmpty()) return null
    return match
        .withScore()
        .run {
            Pair(first.joinToString(""), second)
        }
}

// Get a map of word scores to their string values, e.g. { 6: ["bad", "dab"] }
fun processDictionary(dictionary: List<String>): Map<Int, List<CharArray>> {
    val wordsWithScores = dictionary.map { it.toCharArray() }
        .filter { it.size <= LETTER_LIMIT }
        .map { it.withScore() }
    return wordsWithScores.groupBy({ it.second }, { it.first })
}

// All possible sums of subsets of a list, e.g. given [1, 2, 4] return [(1+2+4), (1+2), (1+4), (2+4), 1, 2, 4]
private fun List<Int>.powerSet(): Set<Int> {
    val n = size
    return (0 until (1 shl n)).map { mask ->
        indices.fold(0) { sum, i ->
            if ((mask and (1 shl i)) != 0) sum + this[i] else sum
        }
    }.toSet()
}
