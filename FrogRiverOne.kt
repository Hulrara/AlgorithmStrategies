import java.util.*
import kotlin.collections.HashMap

fun main() {
    println(FrogRiverOne().solution(5, intArrayOf(1, 2, 3, 4, 5)))
}

class FrogRiverOne {
    fun solution(X: Int, A: IntArray): Int {

        val visit = BooleanArray(X) { false }
        var cnt = 0
        for (i in A.indices) {
            val a = A[i]
            if (a > X || visit[a - 1]) {
                continue
            }
            visit[a - 1] = true
            cnt += 1
            if (cnt == X) {
                return i
            }
        }
        return -1
    }

    fun solution1(X: Int, A: IntArray): Int {

        val visit = BooleanArray(X) { false }
        var cnt = 0
        A.forEachIndexed { index, i ->
            if (i > X || visit[i - 1]){
                return@forEachIndexed
            }
            visit[i - 1] = true
            cnt += 1
            if (cnt == X) {
                return index
            }
        }
        return -1
    }
}