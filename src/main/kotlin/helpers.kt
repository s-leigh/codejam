import codejam.LETTER_SCORES

const val LETTER_LIMIT = 7

// Get a map of word scores to their string values, e.g. { 6: ["bad", "dab"] }
fun processDictionary(dictionary: List<String>): Map<Int, List<CharArray>> {
    val wordsWithScores = dictionary.map { it.toCharArray() }
        .filter{it.size <= LETTER_LIMIT}
        .withScores()
    return wordsWithScores.groupBy({ it.second }, { it.first })
}

// All possible sums of subsets of a list, e.g. given [1, 2, 4] return [(1+2+4), (1+2), (1+4), (2+4), 1, 2, 4]
fun List<Int>.powerSet(): Set<Int> {
    val n = size
    return (0 until (1 shl n)).map { mask ->
        indices.fold(0) { sum, i ->
            if ((mask and (1 shl i)) != 0) sum + this[i] else sum
        }
    }.toSet()
}


private fun CharArray.wordScore(): Int = this.sumOf { LETTER_SCORES[it]!! }
fun List<CharArray>.withScores() = this.map { it to it.wordScore() }
