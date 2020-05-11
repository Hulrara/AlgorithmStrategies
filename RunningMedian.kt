import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val nab = bf.readLine().split(" ").map { it.toInt() }
        val runningMedian = RunningMedian(nab[1], nab[2])
        var sum = 0

        for (n in 1..nab[0]) {
            sum = (sum+runningMedian.solve(runningMedian.constructor(n))) %20090711
        }
        stringBuilder.appendln(sum)
    }
    print(stringBuilder)
}

class RunningMedian(var a: Int, var b: Int) {

    val maxPriorityQueue = PriorityQueue<Int>(Collections.reverseOrder())
    val minPriorityQueue = PriorityQueue<Int>()
    fun solve( rng: Int): Int {

        if (maxPriorityQueue.size == minPriorityQueue.size) {
            maxPriorityQueue.add(rng)
        } else minPriorityQueue.add(rng)

        if (minPriorityQueue.isNotEmpty() && minPriorityQueue.peek() < maxPriorityQueue.peek()) {
            val minFirst = minPriorityQueue.poll()
            val maxFirst = maxPriorityQueue.poll()
            minPriorityQueue.add(maxFirst)
            maxPriorityQueue.add(minFirst)
        }

        return maxPriorityQueue.peek()


    }

    var last = 1983
    fun constructor(n:Int): Int {
        if(n == 1){
            return last
        }
        var ret : Long = last.toLong()
        ret = (ret * a + b) % 20090711
        last = ret.toInt()
        return last
    }

}
