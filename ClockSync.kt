import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val clock = bf.readLine().split(" ").map { it.toInt() }.toMutableList()

    ClockSync().solve(clock as ArrayList<Int>)
}

class ClockSync {
    val button = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 7, 9, 11)
        , intArrayOf(4, 10, 14, 15), intArrayOf(0, 4, 5, 6, 7)
        , intArrayOf(6, 7, 8, 10, 12), intArrayOf(0, 2, 14, 15)
        , intArrayOf(3, 14, 15), intArrayOf(4, 5, 7, 14, 15)
        , intArrayOf(1, 2, 3, 4, 5), intArrayOf(3, 4, 5, 9, 13)
    )

    fun solve(clock: ArrayList<Int>) {
        println(switchPush(clock, 0))
    }

    fun switchPush(clock: ArrayList<Int>, switch: Int): Int {
        var ret = 9999

        if (switch == 10) {
            if (clock.find { it != 12 } == null) {
                return 0
            } else {
                return 9999
            }
        }
        for (i in 0..3) {

            ret = Math.min(ret, i + switchPush(clock, switch + 1))

            button[switch].forEach {
                clock[it] += 3
                if (clock[it] == 15) {
                    clock[it] = 3
                }
            }
        }
        return ret
    }
}