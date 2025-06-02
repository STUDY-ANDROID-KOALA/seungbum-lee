package week7

private fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val jobs = Array(n) {IntArray(2) { 0 } }

    repeat(n) { idx ->
        val (start,end) = readLine().split(" ").map { it.toInt() }
        jobs[idx][0] = start
        jobs[idx][1] = end
    }

    jobs.sortWith(compareBy<IntArray>{ it[1]}.thenBy{ it[0] })

    var cnt = 1
    var endTime = jobs[0][1]

    for(i in 1 until jobs.size) { // n번 반복해서 모든 job을 비교
        if(endTime <= jobs[i][0]){
            endTime = jobs[i][1]
            cnt++
        }
    }
    println(cnt)
}
