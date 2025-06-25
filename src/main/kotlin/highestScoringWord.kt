fun highestScoringWord(letters: CharArray, dictionary: List<String>): Pair<String, Int>? {
    val possibleWords = dictionary
        .map { it.toCharArray() }
        .filter { it.size <= LETTER_LIMIT && wordMatch(letters, it) }
        .withScores()
    return possibleWords.maxByOrNull { it.second }?.run {
        Pair(first.joinToString(""), second)
    }
}
