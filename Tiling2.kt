import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val n = bf.readLine().toInt()
        val tiling2 = Tiling2()
        tiling2.cache = IntArray(n + 1) { -1 }
        stringBuilder.appendln(tiling2.solve2(n))
        stringBuilder.appendln(tiling2.solve3(n))
//        stringBuilder.appendln(tiling2.solve(n) - (tiling2.cache[n / 2] + if (n % 2 == 0) tiling2.cache[n / 2 - 1] else 0))
    }
    println(stringBuilder)
}

class Tiling2 {
    lateinit var cache: IntArray
    fun solve(n: Int): Int {
        if (n <= 1) {
            cache[n] = 1
            return 1
        }

        if (cache[n] != -1) {
            return cache[n]
        }
        val ret = (solve(n - 2) + solve(n - 1)) % 1000000007
        cache[n] = ret
        return ret
    }

    fun solve2(n: Int): Int {
        if (n % 2 == 1) {
            return (solve(n) - solve(n / 2) + 1000000007) % 1000000007
        }
        var ret = solve(n)
        ret = (ret - solve(n / 2) + 1000000007) % 1000000007
        ret = (ret - solve(n / 2 - 1) + 1000000007) % 1000000007
        return ret
    }

    fun solve3(n: Int): Int {
        var ret = solve(n)
        if (n % 2 == 1) {
            return (ret - cache[n / 2] + 1000000007) % 1000000007
        }

        ret = (ret - cache[n / 2] + 1000000007) % 1000000007
        ret = (ret - cache[n / 2 - 1] + 1000000007) % 1000000007
        return ret
    }
}