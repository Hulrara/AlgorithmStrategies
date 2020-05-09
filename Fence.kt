import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val c = br.readLine().toInt()
    val stringBuilder  = StringBuilder()
    for (i in 1..c) {
        val n = br.readLine().toInt()
        val case = br.readLine().split(" ").map { it.toInt() }
        stringBuilder.append("${Fence().solve(case,0,n-1)}\n")
    }
    println(stringBuilder)
}
class Fence{
    fun solve(array : List<Int>,left: Int, right : Int):Int{
        if(left == right){
            return array[left]
        }
        val mid = (left + right) / 2

        var ret = max(solve(array,left,mid),solve(array,mid+1,right))
        var lo = mid
        var hi = mid+1
        var height = min(array[lo],array[hi])
        ret = max(ret, 2*height)

        while (lo>left || hi < right){
            if(lo > left && (hi == right || array[lo - 1] > array[hi + 1])){
                lo--
                height = min(height,array[lo])
            }else{
                hi++
                height = min(height, array[hi])
            }
            ret = max(ret , height * (hi - lo + 1))
        }

        return ret
    }
}