fun main() {
    println(
        ladder().soultion(
            5,
            arrayOf(
                intArrayOf(1, 0, 0, 1),
                intArrayOf(0, 0, 1, 0),
                intArrayOf(0, 1, 0, 0),
                intArrayOf(1, 0, 0, 1),
                intArrayOf(0, 1, 0, 1)
            )
        ).toList()
    )
}

class ladder {
    fun soultion(n: Int, ladder: Array<IntArray>): IntArray {
        var answerArray = IntArray(n)
        for (i in 1..n) {
            var answer = i
            for (h in 0 until ladder.size) {
                if (answer - 2 >= 0 && ladder[h][answer - 2] == 1) {
                    answer -= 1
                    continue
                }
                if (answer - 1 < n - 1 && ladder[h][answer - 1] == 1) {
                    answer += 1
                    continue
                }
            }
            answerArray[i - 1] = answer
        }
        return answerArray
    }
}