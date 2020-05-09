import java.io.BufferedReader
import java.io.InputStreamReader
import javax.xml.stream.events.Characters
import kotlin.math.min

val cache = IntArray(100002)
fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        cache.fill(-1)
        val c = bf.readLine().map { Character.getNumericValue(it) }.toIntArray()
        stringBuilder.appendln(Pi().solve(0, c))
    }
    print(stringBuilder)
}

class Pi {
    fun solve(index: Int, intArray: IntArray): Int {
        if (intArray.isEmpty()) {
            return 0
        }
        if (cache[index] != -1) {
            return cache[index]
        }
        var ret = 99999999
        for (i in 3..5) {
            if (intArray.size >= 3) {
                ret =
                    min(ret, solve(index + i, intArray.drop(i).toIntArray()) + classify(intArray.take(i).toIntArray()))
                cache[index ] = ret
            }
        }
        return ret
    }

    fun classify(intArray: IntArray): Int {
        return when {
            one(intArray) -> {
                1
            }
            two(intArray) -> {
                2
            }
            four(intArray) -> {
                4
            }
            five(intArray) -> {
                5
            }
            else -> {
                10
            }
        }
    }

    fun one(intArray: IntArray): Boolean {
        intArray.forEach {
            if (it != intArray.first()) {
                return false
            }
        }
        return true
    }

    fun two(intArray: IntArray): Boolean {
        if (Math.abs(intArray[1] - intArray[0]) != 1) {
            return false
        }
        val a = intArray.dropLast(1).filterIndexed { index, i ->
            intArray[index + 1] - i != intArray[1] - intArray[0]
        }
        return a.count() == 0
    }

    fun four(intArray: IntArray): Boolean {
        val a = intArray[0]
        val b = intArray[1]
        intArray.drop(2).forEachIndexed { index, i ->
            if ((index % 2 == 0 && i != a) || (index % 2 == 1 && i != b))
                return false
        }
        return true
    }

    fun five(intArray: IntArray): Boolean {
        val diff = intArray[1] - intArray[0]
        for (i in 2 until intArray.size - 1) {
            if (diff != intArray[i + 1] - intArray[1]) {
                return false
            }
        }
        return true
    }
}