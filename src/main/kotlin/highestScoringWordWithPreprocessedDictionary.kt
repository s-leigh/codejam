fun highestScoringWordWithPreprocessedDictionary(
    letters: CharArray,
    dictionary: Map<Int, List<CharArray>>
): Pair<String, Int>? {
    val values = letters.map { LETTER_SCORES[it]!! }
    val possibleWordScores = values.powerSet()
    val possibleWords = possibleWordScores.flatMap { dictionary[it] ?: listOf() }
    return possibleWords
        .filter { wordMatch(letters, it) }
        .withScores()
        .maxByOrNull { it.second }?.run {
            Pair(first.joinToString(""), second)
        }
}

// Get a map of word scores to their string values, e.g. { 6: ["bad", "dab"] }
fun processDictionary(dictionary: List<String>): Map<Int, List<CharArray>> {
    val wordsWithScores = dictionary.map { it.toCharArray() }
        .filter{it.size <= LETTER_LIMIT}
        .withScores()
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
