package week2

private val bw = System.`out`.bufferedWriter()
private lateinit var arr: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)
    backTracking(0, n, m)
    bw.flush()
}


private fun backTracking(k: Int, n: Int, m: Int) {
    if (k == m) {
        for (i in arr.indices) bw.write("${arr[i]} ")
        bw.write("\n")
        return
    }

    for (i in 1..n) {
        arr[k] = i
        backTracking(k + 1, n, m)
    }
}