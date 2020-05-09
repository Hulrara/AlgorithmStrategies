import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val c = br.readLine().toInt()
    val stringBuilder = StringBuilder()
    for(i in 1..c){
        stringBuilder.appendln(Brackets2().solve(br.readLine()))
    }
    print(stringBuilder)
}
class Brackets2() {
    val open = "({["
    val close = ")}]"
    val stack = LinkedList<Char>()
    fun solve(input:String):String{
        input.forEach {
            if(open.contains(it)){
                stack.push(it)
            }else {
                if(stack.isEmpty()){
                    return "NO"
                }
                if(open.indexOf(stack.peek()) != close.indexOf(it)){
                    return "NO"
                }
                stack.pop()
            }
        }
        return "YES"
    }
}