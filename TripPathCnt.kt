import kotlin.math.max

var aar: Array<IntArray> = Array(3) { IntArray(3) { -1 } }
fun main() {
    aar[0][0] = 6
    aar[1][0] = 1
    aar[1][1] = 2
    aar[2][0] = 3
    aar[2][1] = 7
    aar[2][2] = 4
    println(TripPathCnt().path(0, 0))
}

class TripPathCnt {
    val cache = Array(100) { IntArray(100) { -1 } }
    fun path(y: Int,x:Int): Int {
        if (y == aar.size - 1) {
            return aar[y][x]
        }
        if (cache[y][x] != -1) {
            return cache[y][x]
        }
        val ret = max(path(y + 1, x), path(y + 1, x + 1)) + aar[y][x]
        cache[y][x] = ret
        return ret
    }
}