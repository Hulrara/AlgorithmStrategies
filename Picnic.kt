import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val buffer = BufferedReader(InputStreamReader(System.`in`))
    val c = buffer.readLine().toInt()

    var answer = ""
    for (count in 1..c) {
        val nm = buffer.readLine().split(" ")
        val n = nm[0].toInt()
        val m = nm[1].toInt()

        val couple = buffer.readLine().split(" ")
        val students = IntArray(n) { it }.toMutableList() as ArrayList<Int>
        val pairList = ArrayList<Pair<Int, Int>>()
        for (i in couple.indices step 2) {
            val min = Math.min(couple[i].toInt(), couple[i + 1].toInt())
            val max = Math.max(couple[i].toInt(), couple[i + 1].toInt())
            pairList.add(Pair(min, max))
        }
        answer = "$answer\n${Picnic().solve(students, pairList)}"
    }
    print(answer)
}

class Picnic {
    fun solve(students: ArrayList<Int>, couple: ArrayList<Pair<Int, Int>>):Int{
        return couple(students,couple)
    }

    /*
    나의 풀이
    현재 짝이 없는 students, 짝 현황은 couple 로 받는다.
    기저사례 = 더이상 짝을 지을 학생이 없는 경우, 해당 학생으로 지을 짝이 없는경우

    students 의 첫번째 인덱스를 받아 짝을 지을 수 있는 케이스를 모두 찾아, 해당 인덱스와 짝으로 이루어진 인덱스를 제외하고 처리한다.
    이후 해당 students 로 재귀함수 방식으로 해결한다.
    */

    fun couple(students: ArrayList<Int>, couple: ArrayList<Pair<Int, Int>>):Int {
        if (students.isEmpty()) {
            return 1
        }
        val index = students.min()
        val find = couple.filter { it.first == index }
        if(find.isEmpty()){
            return 0
        }

        var ret= 0
        for(f in find){

            students.remove(f.first)
            students.remove(f.second)

            ret += couple(students,couple)

            students.add(f.first)
            students.add(f.second)
        }
        return ret
    }

}