package week1

import kotlin.math.pow

private val bw = System.`out`.bufferedWriter()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    bw.write("${2.0.pow(n).toInt() - 1}\n")
    moveCircle(1, 2, 3, n)
    bw.flush()
    bw.close()
}

fun moveCircle(before: Int, middle: Int, after: Int, n: Int) {
    if (n == 1) {
        bw.write("$before $after\n")
        return
    }
    moveCircle(before, after, middle, n - 1)
    bw.write("$before $after\n")
    moveCircle(middle, before, after, n - 1)
}
//아무리 봐도 모르겟음 포기!