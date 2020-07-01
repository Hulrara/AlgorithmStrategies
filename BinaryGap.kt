fun main() {
    println(BinaryGap().solution(32))
}

class BinaryGap {
    fun solution(N: Int): Int {
        var _N = N
        var max = 0
        var count = -1
        while (_N > 0) {
            if (_N % 2 == 0 && count >= 0) {
                count += 1
            } else if (_N % 2 == 1) {
                if (count >= 0) {
                    max = Math.max(max, count)
                }
                count = 0
            }
            _N /= 2
        }
        return max
    }
}