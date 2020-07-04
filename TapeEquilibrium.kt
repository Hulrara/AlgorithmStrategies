fun main(){
    print(TapeEquilibrium().solution(intArrayOf(10,20,15,30,40)))
}
class TapeEquilibrium {
    fun solution(A: IntArray): Int {
        val sumArray = IntArray(A.size)

        sumArray[0] = A[0]
        for (i in 1 until A.size) {
            sumArray[i] = sumArray[i - 1] + A[i]
        }
        var answer = 10000000
        for (p in 1 until sumArray.size) {
            answer = Math.min(answer, Math.abs(sumArray[p-1] - (sumArray.last() - sumArray[p-1] )))
        }
        return answer
    }
}