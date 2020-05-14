import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()

    val c = bf.readLine().toInt()

    for (i in 1..c) {
        val ndp = bf.readLine().split(" ").map { it.toInt() }
        val numbe3rs = Numbe3rs(ndp[0])
        for (j in 0 until ndp[0]) {
            bf.readLine().split(" ").forEachIndexed { index, s -> numbe3rs.villageArray[j][index] = s.toInt() }
        }
        val t = bf.readLine().toInt()
        numbe3rs.culArray = IntArray(t)
        numbe3rs.culArray = bf.readLine().split(" ").map { it.toInt() }.toIntArray()
        for (answer in 0 until t) {
            numbe3rs.cache = Array(51) { DoubleArray(101) {-1.0} }
//            val path = ArrayList<Int>()
//            path.add(ndp[2])
//            stringBuilder.append(String.format("%.7f ", numbe3rs.solve(path, ndp[1], numbe3rs.culArray[answer])))
            stringBuilder.append(String.format("%.7f ", numbe3rs.solve(ndp[2], ndp[1], numbe3rs.culArray[answer],1)))
        }
        stringBuilder.appendln()
    }
    print(stringBuilder)
}

class Numbe3rs(val n: Int) {
    var villageArray: Array<IntArray> = Array(n) { IntArray(n) }
    lateinit var culArray: IntArray

    fun solve(path: ArrayList<Int>, day: Int, target: Int): Double {
        if (day == 0) {
            return if (path.last() != target)
                0.0
            else{
                return path.dropLast(1).fold(1.0){acc, i ->
                   acc / villageArray[i].count {
                       it ==1
                   }
                }
            }
        }
        var ret = 0.0
        villageArray[path.last()].forEachIndexed { index, i ->
            if (i == 1) {
                path.add(index)
                ret += solve(path, day - 1, target)
                path.removeAt(path.size-1)
            }
        }
        return ret
    }

    lateinit var cache : Array<DoubleArray>
    fun solve(village:Int,day:Int,target: Int,count:Int):Double{
        val _count = villageArray[village].count { it==1 }
        if(day == 0){
            return if(village == target)
                (1.0/count)
            else
                0.0
        }
        if(cache[village][day] > -0.5){
            return cache[village][day]
        }
        var ret = 0.0

        villageArray[village].forEachIndexed { index, i ->
            if(i==1){
                ret += solve(index,day-1,target,count * _count)
                cache[village][day] = ret
            }
        }
        return ret
    }
}