package week2

private val bw = System.`out`.bufferedWriter()
private lateinit var arr: IntArray
private var count = 0


fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    dfs(goal = s, sum = 0, idx = 0)

    if (s == 0) count--
    bw.write("$count")
    bw.flush()
    bw.close()
}

private fun dfs(goal: Int, sum: Int, idx: Int) {
    if (idx == arr.size) {
        if (sum == goal) count++
        return
    }

    dfs(goal, sum + arr[idx], idx + 1)
    dfs(goal, sum, idx + 1)
}
/*
    이건 누적합을 해야하는거 아닐까? 모든 부분수열을 도는데
    선택한것과 안한것 고르기..?
     */