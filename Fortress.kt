import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        val n = bf.readLine().toInt()
        val fortress = Fortress(n)
        for(j in 0 until n){
            val input = bf.readLine().split(" ").map { it.toInt() }
            fortress.x[j] = input[0]
            fortress.y[j] = input[1]
            fortress.r[j] = input[2]
        }
        fortress.root =  fortress.makeNode(0)
        stringBuilder.appendln(fortress.solve(fortress.root ))
    }
    print(stringBuilder)

}

class Fortress(var n: Int) {
    val x = IntArray(n)
    val y = IntArray(n)
    val r = IntArray(n)
    lateinit var root : Node
    var longest = 0

    fun solve(root:Node) : Int{
        val heighgt = getHeights(root)
        return Math.max(longest,heighgt)
    }
    fun getHeights(root:Node) : Int {
        var heights = ArrayList<Int>()

        for (child in root.children){
            heights.add(getHeights(child))
        }

        if(heights.isEmpty()) return  0

        heights.sort()

        if(heights.size>=2)
            longest = Math.max(longest, 2 + heights[heights.size-2] + heights[heights.size-1])
        return heights.last() + 1
    }

    fun makeNode(root:Int) :Node{
        val ret = Node()
        for (ch in 0 until n) {
            if(isChild(root,ch))
                ret.children.add(makeNode(ch))
        }
        return ret
    }

    fun sqr(x:Int) = x * x
    fun sqrdist(a:Int,b:Int) = sqr(y[a] - y[b]) + sqr(x[a] - x[b])
    fun encloses(a:Int , b:Int) = r[a] > r[b] && sqrdist(a,b) < sqr(r[a] - r[b])

    fun isChild(parent:Int,child:Int) :Boolean{
        if(!encloses(parent,child)) return false

        for(i in 0 until n){
            if(i != parent && i!=child && encloses(parent,i) && encloses(i,child))
                return false
        }
        return true
    }
}

class Node() {
    val children: ArrayList<Node> = ArrayList()
}