import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import kotlin.time.seconds

fun main() {
    val buffer = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = buffer.readLine().toInt()
    for (i in 1..c) {
        val nk = buffer.readLine().split(" ").map { it.toInt() }
        val arr = buffer.readLine().split(" ").map { it.toInt() }
        Klis(nk[0], arr).reconstruct(-1,nk[1] ,ArrayList<Int>())
    }
}

class Klis(val n: Int, val arr: List<Int>) {
    val cacheLen = IntArray(500) { -1 }
    val cacheCnt= IntArray(500) { -1 }

    fun lis(start: Int): Int {
        if (cacheLen[start + 1] != -1) {
            return cacheLen[start + 1]
        }
        var ret = 1

        for (next in start + 1 until arr.size) {
            if (start == -1 || arr[start] < arr[next]) {
                ret = Math.max(ret, lis(next) + 1)
            }
        }
        cacheLen[start] = ret
        return ret
    }

    fun count(start : Int):Int{
       if(cacheCnt[start + 1] != -1){
           return cacheCnt[start+1]
       }
        var ret = 0

        for (next in start + 1 until arr.size) {
            if ((start == -1 || arr[start] < arr[next]) && lis(start) == lis(next)+1) {
                ret +=
                    count(next)
            }
        }
        return ret
    }

    fun reconstruct(start:Int,_skip:Int,list: ArrayList<Int>){
        var skip = _skip
        if(start != -1) list.add(arr[start])

        val followers = ArrayList<Pair<Int,Int>>()
        for (next in start + 1 until arr.size) {
            if ((start == -1 || arr[start] < arr[next]) && lis(start) == lis(next)+1) {
              followers.add(Pair(arr[next],next))
            }
        }
        followers.sortWith(Comparator { t, t2 ->
           return@Comparator if(t.first == t2.first){
                t2.second.compareTo(t.second)
            }else{
               t2.first.compareTo(t.first)
           }
        })
        for( i in followers){
            val idx = i.second
            val cnt = count(idx)

            if(cnt <= skip){
                skip-= cnt
            }else{
                reconstruct(idx,skip,list)
                break
            }
        }
    }
}