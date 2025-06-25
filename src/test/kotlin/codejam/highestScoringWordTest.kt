package codejam

import processDictionary
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

const val DICTIONARY_FILENAME = "/dictionary.txt"
val dictionary = object {}.javaClass.getResource(DICTIONARY_FILENAME)?.readText()
    ?: throw IllegalArgumentException("Dictionary file not found: $DICTIONARY_FILENAME")

class highestScoringWordTest {

    @Test
    fun `test real dictionary`() {
        val letters = charArrayOf('I','A','L','B','U','K')
        val dictionary = dictionary.split("\n", "\r\n")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("KUBLAI", 12), result)
    }

    @Test
    fun `test single valid word with unique highest score`() {
        val letters = charArrayOf('C', 'A', 'T', 'K', 'K')
        val dictionary = listOf("CAT", "AT", "TAC", "ATKKC")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("ATKKC", 15), result)
    }

    @Test
    fun `test multiple words with equal scores`() {
        val letters = charArrayOf('B', 'A', 'T')
        val dictionary = listOf("BAT", "TAB")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("BAT", 5), result) // First word in the list should win
    }

    @Test
    fun `test letters forming a subset of a word`() {
        val letters = charArrayOf('B', 'A', 'T')
        val dictionary = listOf("BATTLE")
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No word can be formed
    }

    @Test
    fun `test dictionary with no possible words`() {
        val letters = charArrayOf('X', 'Y', 'Z')
        val dictionary = listOf("CAT", "DOG", "FISH")
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No matches with the dictionary
    }

    @Test
    fun `test empty dictionary`() {
        val letters = charArrayOf('A', 'B', 'C')
        val dictionary = emptyList<String>()
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No results since dictionary is empty
    }

    @Test
    fun `test empty letters array`() {
        val letters = charArrayOf()
        val dictionary = listOf("ANY", "WORD")
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No words can be formed with empty letters
    }

    @Test
    fun `test single possible word in dictionary`() {
        val letters = charArrayOf('I', 'S', 'T')
        val dictionary = listOf("SIT")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("SIT", 3), result) // Single word in the dictionary
    }

    @Test
    fun `test case sensitivity in letters and words`() {
        val letters = charArrayOf('a', 'b', 'c')
        val dictionary = listOf("ABC", "abc")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("ABC", 7), result)
    }
}

class highestScoringWordWithPreprocessedDictionaryTest {

    @Test
    fun `test real dictionary`() {
        val letters = charArrayOf('I','A','L','B','U','K')
        val processedDictionary = processDictionary(dictionary.split("\n", "\r\n"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, processedDictionary)
        assertEquals(Pair("KUBLAI", 12), result)
    }

    @Test
    fun `test single valid word with unique highest score`() {
        val letters = charArrayOf('C', 'A', 'T', 'K', 'K')
        val dictionary = processDictionary(listOf("CAT", "AT", "TAC", "ATKKC"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("ATKKC", 15), result)
    }

    @Test
    fun `test multiple words with equal scores`() {
        val letters = charArrayOf('B', 'A', 'T')
        val dictionary = processDictionary(listOf("BAT", "TAB"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("BAT", 5), result) // First word in the list should win
    }

    @Test
    fun `test letters forming a subset of a word`() {
        val letters = charArrayOf('B', 'A', 'T')
        val dictionary = processDictionary(listOf("BATTLE"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No word can be formed
    }

    @Test
    fun `test dictionary with no possible words`() {
        val letters = charArrayOf('X', 'Y', 'Z')
        val dictionary = processDictionary(listOf("CAT", "DOG", "FISH"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No matches with the dictionary
    }

    @Test
    fun `test empty dictionary`() {
        val letters = charArrayOf('A', 'B', 'C')
        val dictionary = processDictionary(emptyList<String>())
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No results since dictionary is empty
    }

    @Test
    fun `test empty letters array`() {
        val letters = charArrayOf()
        val dictionary = processDictionary(listOf("ANY", "WORD"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No words can be formed with empty letters
    }

    @Test
    fun `test single possible word in dictionary`() {
        val letters = charArrayOf('I', 'S', 'T')
        val dictionary = processDictionary(listOf("SIT"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("SIT", 3), result) // Single word in the dictionary
    }

    @Test
    fun `test case sensitivity in letters and words`() {
        val letters = charArrayOf('a', 'b', 'c')
        val dictionary = processDictionary(listOf("ABC", "abc"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("ABC", 7), result)
    }
}