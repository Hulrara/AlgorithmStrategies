import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

lateinit var array: IntArray
fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val nm = bf.readLine().split(" ")
        val n = nm[0].toInt()
        val inputArray = bf.readLine().split(" ").map { it.toInt() }
        array = IntArray(n) {
            inputArray[it]
        }.sortedArray()

        val m = nm[1].toInt()
        stringBuilder.appendln(Quantization().quantize(0, m))
    }
    print(stringBuilder)
}

class Quantization {

    fun quantize(from: Int, parts: Int): Int {
        if (from == array.size) {
            return 0
        }
        if (parts == 1) {
            return minError(array.drop(from))
        }
        var ret = 987654321
        val drop = array.drop(from)
        for (i in 1 .. drop.size - (parts - 1)) {
            ret = Math.min(ret, minError(drop.take(i)) + quantize(from + i , parts - 1))
//            ret = Math.min(ret, minError(i,from + i -1)+ quantize(i, parts - 1))
        }
        return ret
    }

    fun minError(errorArray: List<Int>): Int {
        if (errorArray.isEmpty()) {
            return 987654321
        }
        var ret = 987654321
        for (i in errorArray.first()..errorArray.last()) {
            ret = Math.min(ret, errorArray.fold(0) { acc, j -> acc + ((j - i) * (j - i)) })
        }
        return ret
    }

    fun minError(lo: Int, hi: Int): Int {
        val errorArray = array.drop(lo).dropLast(hi)
        var ret = 987654321
        for (i in errorArray.first()..errorArray.last()) {
            ret = Math.min(ret, errorArray.fold(0) { acc, j -> acc + ((j - i) * (j - i)) })
        }
        return ret
    }
}