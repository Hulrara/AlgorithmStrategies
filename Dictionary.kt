import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val c = br.readLine().toInt()
    val stringBuilder = StringBuilder()
    for (i in 1..c) {
        val n = br.readLine().toInt()
        val dictionary = Dictionary(n)
        for (j in 0 until n) {
            dictionary.strArray[j] = br.readLine()
        }
        dictionary.makeGraph()
        stringBuilder.appendln(dictionary.solve())
    } 
    print(stringBuilder)
}

class Dictionary(n: Int) {
    val strArray = Array(n) { "" }
    val graph = Array<BooleanArray>(26) { BooleanArray(26) { false } }
    val visit = BooleanArray(26){false}
    val order = ArrayList<Int>()
    fun solve():String{
        for (i in graph.indices){
            if(!visit[i]){
                 dfs(i)
            }
        }


        for (i in graph.indices){
            val reverse = order.reversed()
            for(j in i+1 until  graph.size){
                if(graph[reverse[j]][reverse[i]])
                    return "INVALID HYPOTHESIS"
            }
        }
        return order.map { Character.toString(it + 'a'.toInt()) }.toString()
    }
    fun dfs(here : Int){
        visit[here] = true

        for(i in graph.indices){
            if(graph[here][i] && !visit[i]){
                dfs(i)
            }
        }
        order.add(here)
    }
    fun makeGraph() {
        strArray.dropLast(1).forEachIndexed { index, s ->
            val length = Math.min(s.length,strArray[index+1].length)
            for (i in 0 until length){
                val origin = s[i]
                val compare = strArray[index + 1][i]
                if (origin != strArray[index + 1][i]) {
                    graph[compare - 'a'][origin - 'a'] = true
                    break
                }
            }
        }

    }


}