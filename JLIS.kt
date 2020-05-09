import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val nm = bf.readLine().split(" ").map { it.toInt() }
        val a = bf.readLine().split(" ").map { it.toInt() }.toIntArray()
        val b = bf.readLine().split(" ").map { it.toInt() }.toIntArray()
        val ab = a + b
        //stringBuilder.appendln(JLIS().solve(ab.sorted().toIntArray()))
        val cache = Array(101) { IntArray(101) { -1 } }
        stringBuilder.appendln(JLIS().solve2(cache,-1,-1,a,b)-2)

    }
    print(stringBuilder)
}

class JLIS {
    fun solve(ab: IntArray): Int {
        val c = IntArray(ab.size) { -1 }
        c[0] = ab[0]
        var index = 0
        for (i in 1 until ab.size) {
            if (c[index] < ab[i]) {
                c[++index] = ab[i]
            } else {
                c[lower_bound(c, index, ab[i])] = ab[i]
            }
        }
        return index + 1
    }

    fun lower_bound(c: IntArray, end: Int, n: Int): Int {
        var start = 0
        var _end = end
        while (start < _end) {
            var mid = (start + _end) / 2
            if (c[mid] >= n) {
                _end = mid
            } else {
                start = mid + 1
            }
        }
        return _end
    }


    fun solve2(cache:Array<IntArray>,aIndex: Int, bIndex: Int, a: IntArray, b: IntArray): Int {


        if (cache[aIndex + 1][bIndex + 1] != -1) {
            return cache[aIndex + 1][bIndex + 1]
        }
        var ret = 2
        val max = Math.max(if(aIndex == -1) -9 else a[aIndex],if(bIndex == -1) -9 else b[bIndex])
        for (aNext in aIndex + 1 until a.size) {
            if (a[aNext] > max) {
                ret = Math.max(ret, solve2(cache,aNext,bIndex,a,b) + 1)
                cache[aIndex +1][bIndex +1] = ret
            }
        }
        for (bNext in bIndex + 1 until b.size) {
            if ( b[bNext] > max) {
                ret = Math.max(ret, solve2(cache,aIndex,bNext,a,b) + 1)
                cache[aIndex+1 ][bIndex+1] = ret
            }
        }
        return ret
    }
}