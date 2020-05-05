import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

val cache = Array(101) { IntArray(101) { -1 } }
fun main() {

    val bf = BufferedReader(InputStreamReader(System.`in`))
    val c = bf.readLine().toInt()
    val sb = StringBuilder()

    for (i in 1..c) {
        val wildCard = bf.readLine()
        val n = bf.readLine().toInt()

        for (j in 1..n) {
            val check = bf.readLine()
            cache.fill(IntArray(101){-1},0,cache.size)
//            if (WildCard().solve(wildCard, check))
//                sb.appendln(check)
            if (WildCard().memoSolve(wildCard, check, 0, 0) == 1)
                sb.appendln(check)
        }
    }
    print(sb)
}

class WildCard {

    fun solve(wildCard: String, check: String): Boolean {
        var pos = 0

        wildCard.forEach {
            if (it == '*') {
                for (i in check.indices) {
                    if (solve(wildCard.drop(pos + 1), check.drop(pos + i)))
                        return true
                }
            } else if (it == '?') {
                pos++
            } else {
                if (pos >= check.length || it != check[pos])
                    return false
                pos++
            }

        }
        return check.length == pos
    }

    fun memoSolve(_wildCard: String, _check: String, wildCardIndex: Int, checkIndex: Int): Int {

        val wildCard = _wildCard.drop(wildCardIndex)
        val check = _check.drop(checkIndex)
        var wl = wildCardIndex
        var cl = checkIndex
        if (cache[wl][cl] != -1) {
            println("$wl $cl 중복")
            return cache[wl][cl]
        }
        wildCard.forEachIndexed { index, it ->

            if (it == '*') {
                for (i in check.indices) {
                    if (memoSolve(_wildCard, _check, wl + 1, cl + i) == 1) {
                        cache[wl][cl] = 1
                        return cache[wl][cl]
                    }
                }
            } else if (it == '?') {
                wl++
                cl++
            } else {
                if (cl >= _check.length || it != _check[cl]) {
                    cache[wl][cl] = 0
                    return cache[wl][cl]
                }
                wl++
                cl++
            }

        }
        cache[wl][cl] =  if(_check.length == cl)  1 else 0
        return cache[wl][cl]

    }
}