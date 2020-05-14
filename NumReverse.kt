fun main() {
    NumReverse().solve(-1234)
}

class NumReverse {
    fun solve(_num: Int) {
        var num = _num
        val length = Math.log10(num.toDouble()).toInt() +1
        var answer: Double = 0.0
        for (i in 1..length) {
            val a = num % 10
            num /= 10
            answer += a * Math.pow(10.toDouble(), length.toDouble() - i)
        }
        print(answer.toInt())
    }
}