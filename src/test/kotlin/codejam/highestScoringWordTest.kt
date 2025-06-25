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
        val dictionaryFile = dictionary.split("\n", "\r\n")
        val dictionary = dictionaryFile.take(dictionaryFile.size - 2)
        val input = dictionaryFile.subList(dictionaryFile.size - 2, dictionaryFile.size-1).single().toCharArray()
        val result = highestScoringWord(input, dictionary)
        assertEquals(Pair("pastern", 9), result)
    }

    @Test
    fun `test single valid word with unique highest score`() {
        val letters = charArrayOf('c', 'a', 't', 'k', 'k')
        val dictionary = listOf("cat", "at", "tac", "atkkc")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("atkkc", 15), result)
    }

    @Test
    fun `test multiple words with equal scores`() {
        val letters = charArrayOf('b', 'a', 't')
        val dictionary = listOf("bat", "tab")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("bat", 5), result) // First word in the list should win
    }

    @Test
    fun `test letters forming a subset of a word`() {
        val letters = charArrayOf('b', 'a', 't')
        val dictionary = listOf("battle")
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No word can be formed
    }

    @Test
    fun `test dictionary with no possible words`() {
        val letters = charArrayOf('x', 'y', 'z')
        val dictionary = listOf("cat", "dog", "fish")
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No matches with the dictionary
    }

    @Test
    fun `test empty dictionary`() {
        val letters = charArrayOf('a', 'b', 'c')
        val dictionary = emptyList<String>()
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No results since dictionary is empty
    }

    @Test
    fun `test empty letters array`() {
        val letters = charArrayOf()
        val dictionary = listOf("any", "word")
        val result = highestScoringWord(letters, dictionary)
        assertNull(result) // No words can be formed with empty letters
    }

    @Test
    fun `test single possible word in dictionary`() {
        val letters = charArrayOf('i', 's', 't')
        val dictionary = listOf("sit")
        val result = highestScoringWord(letters, dictionary)
        assertEquals(Pair("sit", 3), result) // Single word in the dictionary
    }
}

class highestScoringWordWithPreprocessedDictionaryTest {
    val dictionaryFile = dictionary.split("\n", "\r\n")
    val processedDictionary = processDictionary(dictionaryFile.take(dictionaryFile.size - 2))
    @Test
    fun `test real dictionary`() {
        val input = dictionaryFile.subList(dictionaryFile.size - 2, dictionaryFile.size-1).single().toCharArray()
        val result = highestScoringWordWithPreprocessedDictionary(input, processedDictionary)
        assertEquals(Pair("pastern", 9), result)
    }

    @Test
    fun `test single valid word with unique highest score`() {
        val letters = charArrayOf('c', 'a', 't', 'k', 'k')
        val dictionary = processDictionary(listOf("cat", "at", "tac", "atkkc"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("atkkc", 15), result)
    }

    @Test
    fun `test multiple words with equal scores`() {
        val letters = charArrayOf('b', 'a', 't')
        val dictionary = processDictionary(listOf("bat", "tab"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("bat", 5), result) // First word in the list should win
    }

    @Test
    fun `test letters forming a subset of a word`() {
        val letters = charArrayOf('b', 'a', 't')
        val dictionary = processDictionary(listOf("battle"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No word can be formed
    }

    @Test
    fun `test dictionary with no possible words`() {
        val letters = charArrayOf('x', 'y', 'z')
        val dictionary = processDictionary(listOf("cat", "dog", "fish"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No matches with the dictionary
    }

    @Test
    fun `test empty dictionary`() {
        val letters = charArrayOf('a', 'b', 'c')
        val dictionary = processDictionary(emptyList<String>())
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No results since dictionary is empty
    }

    @Test
    fun `test empty letters array`() {
        val letters = charArrayOf()
        val dictionary = processDictionary(listOf("any", "word"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertNull(result) // No words can be formed with empty letters
    }

    @Test
    fun `test single possible word in dictionary`() {
        val letters = charArrayOf('i', 's', 't')
        val dictionary = processDictionary(listOf("sit"))
        val result = highestScoringWordWithPreprocessedDictionary(letters, dictionary)
        assertEquals(Pair("sit", 3), result) // Single word in the dictionary
    }
}