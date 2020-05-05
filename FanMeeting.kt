import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val members = bf.readLine().toCharArray()
        val fans = bf.readLine().toCharArray()
        println(FanMeeting().solve(members,fans))
    }
}

class FanMeeting {
    fun solve(members: CharArray, fans: CharArray): Int {
        var answer = 0
        var count = 0
        loop@while (count < fans.size) {
            val drop = fans.drop(count)
            count++
            if(drop.size <members.size){
                return answer
            }

            for (i in members.indices) {
                if(members[i] == 'M' && members[i] == drop[i]){
                    continue@loop
                }
            }
            answer++
        }
        return  answer
    }
}