package scrabble

fun highestScoringWordFewestCharacters(l: CharArray, d: List<String>): Pair<String, Int>? =
    d.map { it.toCharArray() }
        .filter { it.size <= 7 && m(l, it) }
        .map { it to it.sumOf { L[it]!! } }
        .maxByOrNull { it.second }?.run {
        Pair(first.joinToString(""), second)
    }

private fun m(l: CharArray, w: CharArray): Boolean {
    if (!w.all{it in l}) return false
    val letterCount = l.frequencyMap()
    w.frequencyMap().forEach{ (c, n) ->
        if (letterCount[c]!! < n) return false
    }
    return true
}

private val L =
    "aeioulnrst".associate { it to 1 } +
            "dg".associate { it to 2 } +
            "bcmp".associate { it to 3 } +
            "fhvwy".associate { it to 4 } +
            "k".associate { it to 5 } +
            "jx".associate { it to 8 } +
            "qz".associate { it to 10 }