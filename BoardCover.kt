import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    var bf = BufferedReader(InputStreamReader(System.`in`))
    val c = bf.readLine().toInt()

    for (i in 1..c) {
        val hw = bf.readLine().split(" ")
        val h = hw[0].toInt()
        val w = hw[1].toInt()
        val board = Array(h) { BooleanArray(w) { false } }
        for (j in 0 until h) {
            bf.readLine().toList().forEachIndexed { index, c ->
                if (c == '#') board[j][index] = true else if (c == '.') board[j][index] = false
            }
        }
        print(BoardCover().solve(board,h,w))
    }
}

class BoardCover {
    //덮는 경우의수 풀이참
    val checkArray = arrayOf(
        arrayOf(intArrayOf(1, 0), intArrayOf(0, 1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(1, 1)),
        arrayOf(intArrayOf(0, 1), intArrayOf(-1, 1)),
        arrayOf(intArrayOf(0, 1), intArrayOf(1, 1))
    )

    fun solve(board: Array<BooleanArray>,h :Int, w:Int): Int {

        var x = -1
        var y = -1

        y = board.indexOfFirst {
            x = it.indexOfFirst { inner ->
                !inner
            }
            x >= 0
        }

        if (x <= -1 || y <= -1) {
            return 1
        }
        var ret = 0
        var isExist = false

        loop@ for (check in checkArray) {

            for (c in check) {
                val dx = x +  c[0]
                val dy = y +  c[1]

                if (dx >= w || dy >= h || board[dy][dx]) {
                    continue@loop
                }
            }
            isExist = true

            board[y][x] = true
            for (c in check) {
                val dx = x +  c[0]
                val dy = y +  c[1]
                board[dy][dx] = true
            }

            ret += solve(board,h,w)

            board[y][x] = false
            for (c in check) {
                val dx = x +  c[0]
                val dy = y +  c[1]
                board[dy][dx] = false
            }
        }
        if (!isExist) {
            return 0
        }
        return ret
    }
}