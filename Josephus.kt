import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val c = br.readLine().toInt()
    val stringBuilder = StringBuilder()
    for(i in 1..c){
        val nk = br.readLine().split(" ").map { it.toInt() }
        val n = nk[0]
        val k = nk[1]
        val answer = Josephus(n).solve(k)
        stringBuilder.appendln("${answer[0]} ${answer[1]}")
    }
    println(stringBuilder)
}
class Josephus(val n : Int) {
    val linkedList = LinkedList<Int>(IntArray(n){it+1}.toList())
    fun solve(_k:Int):IntArray{
        var kill = 0
        var next = 0
//        while (linkedList.size>2){
//            val index = linkedList.indexOf(kill)
//            next = linkedList[(index+_k) % linkedList.size]
//            linkedList.remove(kill)
//            kill = next
//         }
        while (linkedList.size>2){
            linkedList.removeAt(kill)
            kill = (kill+_k - 1) % linkedList.size
        }
        return linkedList.toIntArray()
    }
}
