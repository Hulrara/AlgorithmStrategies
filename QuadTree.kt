import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    for (i in 1..n) {
        val case = br.readLine()
        println(QuadTree().solve(case.toCharArray()))
    }
}

class QuadTree {
    fun solve(case: Array<Char>, y: Int, x: Int, size: Int) {
        if (case[0] == 'w') {
            println("w")
        } else if (case[0] == 'b') {
            println("b")
        } else {
            case.drop(1)
        }
    }
    var count = 0
    fun solve(case: CharArray): String {
        if(count == case.size){
            return ""
        }
        val head = case[count]
        count ++
        return if (head == 'w') {
            "w"
        } else if (head == 'b') {
            "b"
        } else {
            var array = case.drop(count).toCharArray()
            val upperLeft: String = solve(array)
            val upperRight: String = solve(array)
            val lowerLeft: String = solve(array)
            val lowerRight: String = solve(array)
            "x$lowerLeft$lowerRight$upperLeft$upperRight"
        }
    }
}