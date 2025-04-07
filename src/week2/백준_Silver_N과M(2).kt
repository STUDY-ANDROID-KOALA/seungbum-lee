package week2


private val bw = System.`out`.bufferedWriter()
private lateinit var visited: BooleanArray
private lateinit var arr: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    visited = BooleanArray(n)
    arr = IntArray(m + 1)

    backTracking(1, n,m)
    bw.flush()
}

fun backTracking(k: Int, n: Int, m: Int) {
    if (k == m + 1) {
        for (i in 1..m) bw.write("${arr[i]} ")
        bw.write("\n")
        return
    }

    for (i in visited.indices) {
        if (visited[i] || arr[k - 1] > i + 1) continue
        visited[i] = true
        arr[k] = i + 1
        backTracking(k + 1, n, m)
        visited[i] = false
    }
}
/*
    i+1이 실제로 적히는 숫자
    arr[k]는 순서대로 출력대는 수샂.
 */