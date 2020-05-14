import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val stringBuilder = StringBuilder()
    val c = bf.readLine().toInt()
    for (i in 1..c) {
        bf.readLine().toInt()
        val preorder = bf.readLine().split(" ").map { it.toInt() }
        val inorder = bf.readLine().split(" ").map { it.toInt() }
        Traversal().solve(preorder,inorder)
        println()
    }

}
class Traversal {
    fun solve(preorder:List<Int>, inorder:List<Int>){


        if(preorder.isEmpty()){
            return
        }
        val root = preorder[0]
        val inorderRootIndex = inorder.indexOf(root)
        val preorderLeft = preorder.subList(1,inorderRootIndex +1)
        val preorderRight = preorder.subList(inorderRootIndex+1,inorder.size)
        val inorderLeft = inorder.subList(0,inorderRootIndex)
        val inorderRight = inorder.subList(inorderRootIndex+1,inorder.size)

        solve(preorderLeft,inorderLeft)
        solve(preorderRight,inorderRight)

        print("$root ")
    }
}