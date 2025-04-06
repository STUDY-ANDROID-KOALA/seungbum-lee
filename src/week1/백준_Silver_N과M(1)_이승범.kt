package week1


private val bw = System.out.bufferedWriter()
private lateinit var arr: IntArray
private lateinit var visited: BooleanArray


fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)
    visited = BooleanArray(n + 1)

    backTracking(0, m, n)
    bw.flush()
    bw.close()
}

fun backTracking(k: Int, m: Int, size: Int) {
    if (k == m) {
        for (i in arr.indices) bw.write("${arr[i]} ")
        bw.write("\n")
        return
    }

    for (i in 1..size) {
        if (!visited[i]) {
            arr[k] = i
            visited[i] = true
            backTracking(k + 1, m, size)
            visited[i] = false
        }
    }
}
//bfs의 기본 구조 visited를 썻다가 되돌려놓는거 반드시 기억해!