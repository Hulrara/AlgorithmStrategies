fun main() {
    print(FrogJmp().solution(10,75,30))
}
class FrogJmp {

    fun solution(X: Int, Y: Int, D: Int): Int {
        var distance = Y - X
        var answer = 0
        answer = distance / D
        if (distance % D > 0) {
            answer += 1
        }
        return answer
    }
}