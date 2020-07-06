import java.util.*

fun main() {
    println(FrogRiverOne().solution(5, intArrayOf(1,3,1,4,2,3,5,4)))
}
class FrogRiverOne {
    fun solution(X: Int, A: IntArray): Int {
        var max = -1
        A.mapIndexed { index, i -> Pair<Int, Int>(index, i) }
            .groupBy { it.second }
            .map { it.value.minBy { it.first}!! }
            .sortedBy { it.second }
            .filter { it.second <= X }
            .forEach {
                max = Math.max(max,it.first)
            }
        return max
    }
}