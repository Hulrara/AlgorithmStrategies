import java.util.*

fun main() {
    print(CyclicRotation().solution(intArrayOf(3,8,9,7,6), 3).toList())
}

class CyclicRotation {
    fun solution(A: IntArray, K: Int): IntArray {
        var _K = K % A.size

        val deque: ArrayDeque<Int> = ArrayDeque<Int>().apply {
            addAll(A.toList())
        }

        while (_K > 0) {
            val last = deque.removeLast()
            _K--
            deque.addFirst(last)
        }
        return deque.toIntArray()
    }
}