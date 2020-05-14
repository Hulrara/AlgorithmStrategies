import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(){
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val c = bf.readLine().toInt()
    val sb = StringBuilder()

    for(i in 1..c){
        val n = bf.readLine().toInt()
        val intArray = bf.readLine().split(" ").map { it.toInt() }.toIntArray()
        sb.appendln(SortingGame().solve(intArray))
    }
    print(sb)
}
class SortingGame() {
    //너비 우선 탐색
    fun solve(array:IntArray):Int{
        val queue = LinkedList<IntArray>()

        val sorted = array.sortedArray()
        val distance = HashMap<String,Int>()

        queue.push(array)
        distance[array.toList().toString()] = 0
        while (queue.isNotEmpty()){
            val here = queue.poll()
            val cost = distance[here.toList().toString()]?:0
            if(sorted.contentEquals(here)){
                return distance[here.toList().toString()]?:0
            }

            for (i in here.indices){
                for (j in i+1 until here.size){
                    val reverse = here.sliceArray(IntRange(i,j)).reversedArray()
                    var new = here.clone()
                    for(k in reverse.indices){
                        new[k+i] = reverse[k]
                    }
                    if(distance[new.toList().toString()] == null){
                        distance[new.toList().toString()] = cost+1
                        queue.add(new)
                    }
                }
            }
        }
        return -1
    }
}