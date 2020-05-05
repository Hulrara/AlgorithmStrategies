import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val n = bf.readLine().toInt()

    }
    println(stringBuilder)
}

class Poly {
    fun solve(n: Int, height: Int): Int {
        if (n == 0) {
            return 0
        }
        if (height == 0) {
            return 0
        }
        var ret = 0
        for (i in 1..n) {
            ret += solve(n - i, height - 1)
        }
        return ret
    }
}