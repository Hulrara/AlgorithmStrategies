fun main() {
    print(PermMissingElem().solution1(intArrayOf(1,3)))
}
class PermMissingElem {
    fun solution(A: IntArray): Int {
        val sum: Long = A.sum().toLong()
        val n = A.size + 1
        val total: Long = (n * (n + 1) / 2).toLong()

        return (total - sum).toInt()
    }

    fun solution1(A: IntArray): Int {
        A.sort()
        var answer = 1
        for (i in A) {
            if (i != answer) {
                break
            }
            answer += 1
        }
        return answer
    }
}