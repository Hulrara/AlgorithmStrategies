import java.util.*
import kotlin.collections.HashMap

fun main() {
    print(OddOccurrencesInArray().solution(intArrayOf(9, 3, 9, 3, 9, 7, 9)))
}

class OddOccurrencesInArray {
    fun solution(A: IntArray): Int {
        var answer = 0
        for (i in A) {
            answer = answer xor i
        }
        return answer
    }

    fun solution1(A: IntArray): Int {
        var answer = 0
        A.sort()

        for (i in 0..A.size step 2) {
            if (i + 1 == A.size) {
                answer = A[i]
                break
            }
            if (A[i] != A[i + 1]) {
                answer = A[i]
                break
            }
        }
        return answer
    }
}