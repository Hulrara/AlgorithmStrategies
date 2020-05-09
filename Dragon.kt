import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val npl = bf.readLine().split(" ").map { it.toInt() }
        val dragon = Dragon(npl[0])
        dragon.precalc()
        for(j in 0 until npl[2]) {
            print(dragon.solve("FX", npl[0], npl[1] - 1 +j))
        }
        println()
    }
}

class Dragon(val n: Int) {
    val length = IntArray(51) { -1 }
    val MAX = 1000000000 + 1
    val map = HashMap<String, String>().also {
        it["X"] = "X+YF"
        it["Y"] = "FX-Y"
    }

    fun solve(seed: String, generations: Int, _skip: Int): Char {
        var skip = _skip
        if (generations == 0) {
            assert(skip < seed.length)
            return seed[skip]
        }
        seed.forEachIndexed { index, it ->
            if (it == 'X' || it == 'Y') {
                if (skip >= length[generations])
                    skip -= length[generations]
                else if (it == 'X') {
                    return solve(map[it.toString()]!!, generations - 1, skip)
                } else {
                    return  solve(map[it.toString()]!!, generations - 1, skip)
                }
            } else if (skip > 0)
                --skip
            else
                return seed[index]
        }
        return '#'
    }

    fun precalc() {
        length[0] = 1
        for (i in 1..50) {
            length[i] = min(MAX, length[i - 1] * 2 + 2)
        }
    }
}