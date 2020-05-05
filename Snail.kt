fun main() {

}

val m = 6
val n = 10

class Snail {
    val cache = Array(n){IntArray(m*2){-1} }
    fun solve(climbed: Int, day: Int): Int {
        if (day == m) {
            return if (climbed >= n) 1 else 0
        }
        if(cache[day][climbed] != -1){
            return cache[day][climbed]
        }
        val ret = solve(climbed + 1, day + 1) + solve(climbed + 1, day + 2)
        cache[day][climbed] = ret
        return ret
    }
}