import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val buffer = BufferedReader(InputStreamReader(System.`in`))
    val nm = buffer.readLine().split(" ").map { it.toInt() }
    val morse = Morse(buffer.readLine().toInt() - 1)
    morse.generate(nm[0], nm[1], "")
}

class Morse(var skip: Int) {
    //모든 모스부호만들기

    fun generate(n: Int, m: Int, s: String) {
        if (skip < 0) return
        if (n == 0 && m == 0) {
            if (skip == 0)
                println(s)
            skip--
            return
        }
        if (n > 0) generate(n - 1, m, "${s}-")
        if (m > 0) generate(n, m - 1, "${s}o")
    }
}