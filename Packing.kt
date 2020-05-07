import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val buffer = BufferedReader(InputStreamReader(System.`in`))
    val c = buffer.readLine().toInt()
    val stringBuilder = StringBuilder()
    for (i in 1..c) {
        val nw = buffer.readLine().split(" ").map { it.toInt() }
        val items = ArrayList<Triple<String, Int, Int>>()
        for (j in 0 until nw[0]) {
            val item = buffer.readLine().split(" ")
            items.add(Triple<String, Int, Int>(item[0], item[1].toInt(), item[2].toInt()))

        }
        val packing = Packing()
        packing.items = items
        packing.n = nw[0]
        packing.solve(nw[1],0)
        packing.reconstruct(nw[1],0)
        stringBuilder.appendln("${packing.answer.sumBy { it.third }} ${packing.answer.size}")
        for (a in packing.answer){
            stringBuilder.appendln(a.first)
        }
    }
    print(stringBuilder)
}

class Packing {

    var n: Int = 0
    lateinit var items: ArrayList<Triple<String, Int, Int>>
    var cache = Array(1001){IntArray(101){-1} }
    var answer = ArrayList<Triple<String, Int, Int>>()
    fun solve(w: Int, index: Int): Int {

        if (index == n) {
            return 0
        }

        if(cache[w][index] != -1){
            return cache[w][index]
        }
        var ret = solve(w, index + 1)

        if (items[index].second <= w) {
            ret = Math.max(ret, solve(w - items[index].second, index + 1) + items[index].third)
        }
        cache[w][index] = ret
        return ret
    }

    fun reconstruct(w: Int, index: Int){
        if(index == n){
            return
        }
        if(solve(w,index+1) == solve(w,index)){
            reconstruct(w,index+1)
        }else{
            answer.add(items[index])
            reconstruct(w-items[index].second,index+1)
        }
    }
}