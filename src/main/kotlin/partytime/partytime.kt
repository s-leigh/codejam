package partytime

data class Place(
    val cost: Int,
    val funFactor: Int,
    val travelTime: Int,
    val hasDude: Boolean
)

fun planNightOut(places: List<Place>, budget: Int, maxTime: Int, dudeBonus: Int): Int? {
    val combinations = generateAllCombinations(places)
    val validCombinations = combinations.filter {
        it.totalCost() <= budget && it.timeValue(dudeBonus) <= maxTime
    }
    val optimal = validCombinations.maxByOrNull { it.funValue() }
    println(optimal)
    return optimal?.funValue()
}

private fun List<Place>.funValue(): Int = this.sumOf { it.funFactor }

private fun List<Place>.timeValue(dudeBonus: Int): Int =
    this.sumOf { it.travelTime } - (this.filter { it.hasDude }.size * dudeBonus)

private fun List<Place>.totalCost(): Int = this.sumOf { it.cost }

private fun generateAllCombinations(places: List<Place>): List<List<Place>> {
    val result = mutableListOf(emptyList<Place>())
    for (place in places) {
        for (i in 0 until result.size) {
            result.add(result[i] + place)
        }
    }
    return result.filter { it.isNotEmpty() }
}
