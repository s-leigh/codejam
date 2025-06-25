const val LETTER_LIMIT = 7

val LETTER_SCORES =
    "aeioulnrst".associate { it to 1 } +
            "dg".associate { it to 2 } +
            "bcmp".associate { it to 3 } +
            "fhvwy".associate { it to 4 } +
            "k".associate { it to 5 } +
            "jx".associate { it to 8 } +
            "qz".associate { it to 10 }

fun wordMatch(letters: CharArray, word: CharArray): Boolean {
    if (!word.all{it in letters}) return false
    val letterCount = letters.frequencyMap()
    word.frequencyMap().forEach{(c, n) ->
        if (letterCount[c]!! < n) return false
    }
    return true
}

private fun CharArray.wordScore(): Int = this.sumOf { LETTER_SCORES[it]!! }
fun List<CharArray>.withScores() = this.map { it to it.wordScore() }

fun CharArray.frequencyMap() = this.groupBy { it }.mapValues { it.value.size }
