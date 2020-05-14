import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val n = bf.readLine().toInt()
        val nerd2 = Nerd2()
        var answer =0
        for (j in 1..n) {
            val ab = bf.readLine().split(" ").map { it.toInt() }
            answer += nerd2.solve(ab[0],ab[1])
        }
        stringBuilder.appendln(answer)
    }
    print(stringBuilder)
}

class Nerd2 {
    var participant = ArrayList<Pair<Int,Int>>()

    fun solve(a:Int,b:Int): Int {
        participant.add(Pair(a,b))
        if(participant.size == 1){
            return 1
        }

        val sorted =  participant.sortedBy{
            it.first + it.second
        }
        participant = sorted.dropWhile { it.first + it.second < a+b && it.first<a && it.second < b } as ArrayList<Pair<Int, Int>>
        return participant.size
    }
}