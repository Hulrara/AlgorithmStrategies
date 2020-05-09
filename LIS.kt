import kotlin.math.max

lateinit var array: IntArray
fun main() {
//    array = intArrayOf(10, 20, 40, 25, 20, 50, 30, 70, 85)
    array = intArrayOf(10,40,70,30)
    println(LIS().solve(array))
    var length = -1
    var lis = LIS()
    for (i in array.indices){
        length = max(length,lis.solve2(i))
    }
    println(length)
    println(LIS().solve3(-1) - 1)
    println(LIS().solve4())
}

class LIS {
    fun solve(array: IntArray): Int {
        if (array.isEmpty()) {
            return 0
        }
        var ret = 1
        for (i in array.indices) {
            val b = ArrayList<Int>()
            for (j in i + 1 until array.size) {
                if (array[i] < array[j]) {
                    b.add(array[j])
                }
            }
            ret = max(ret, 1 + solve(b.toIntArray()))
        }
        return ret
    }

    val cache = IntArray(array.size) { -1 }
    fun solve2(start: Int): Int {
        if (cache[start] != -1) {
            return cache[start]
        }
        var ret = 1
//        array.drop(index+1).forEachIndexed { _index, i ->
//            if (i > array[index]) {
//                ret = Math.max(ret, solve2(index + 1 + _index) + 1)
//                cache[index + 1 + _index] = ret
//            }
//        }
        for (i in start + 1 until array.size) {
            if ( array[start] < array[i]) {
                ret = max(ret, solve2(i) + 1)
            }
            cache[start] = ret
        }
        return ret
    }

    val cache2 = IntArray(array.size + 1) { -1 }
    fun solve3(start: Int): Int {
        if (cache2[start + 1] != -1) {
            return cache2[start + 1]
        }
        var ret = 1
//        var returnIndex = index
//        array.drop(index + 1).forEachIndexed { _index, i ->
//            if (index == -1 || i > array[_index]) {
//                returnIndex += _index +1
//                ret = Math.max(ret, solve3(returnIndex) +1)
//                cache2[returnIndex] = ret
//            }
//        }
        for (i in start + 1 until array.size) {
            if (start == -1 || array[start] < array[i]) {
                ret = max(ret, solve3(i) + 1)
            }
            cache2[start+1] = ret
        }
        return ret
    }

    val answerArray = ArrayList<Int>()
    val c = IntArray(array.size) { -1 }
    fun solve4(): Int {
        var index = 0
        c[0] = array[0]

        for (i in 1 until array.size) {
            if (c[index] < array[i]) {
                c[++index] = array[i]
            } else {
                c[lower_bound(index, array[i])] = array[i]
            }
        }
        return index + 1
    }

    fun lower_bound(end: Int, n: Int): Int {
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

}