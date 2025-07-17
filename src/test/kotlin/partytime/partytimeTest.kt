package partytime

import kotlin.test.Test
import kotlin.test.assertEquals

class partytimeTest {

    @Test
    fun test() {
        val places = listOf(
            Place(cost = 10, funFactor = 5, travelTime = 10, hasDude = false),
            Place(cost = 7, funFactor = 4, travelTime = 6, hasDude = true),
            Place(cost = 12, funFactor = 8, travelTime = 15, hasDude = false),
            Place(cost = 8, funFactor = 6, travelTime = 9, hasDude = true),
            Place(cost = 14, funFactor = 9, travelTime = 20, hasDude = false),
            Place(cost = 6, funFactor = 3, travelTime = 5, hasDude = false),
            Place(cost = 11, funFactor = 7, travelTime = 12, hasDude = false),
            Place(cost = 9, funFactor = 6, travelTime = 8, hasDude = true)
        )
        val result = planNightOut(places, 30, 35, 10)
        assertEquals(20, result)
    }
}
