package week8

private val bw = System.`out`.bufferedWriter()
private var n = 0
private var count = 0
private lateinit var column: BooleanArray
private lateinit var diag1: BooleanArray
private lateinit var diag2: BooleanArray

fun recursive(y: Int) {
    if (y == n) {
        count++
        return
    }
    for (x in 0 until n) {
        if (!column[x] && !diag1[x - y + n - 1] && !diag2[x + y]) {
            column[x] = true
            diag1[x - y + n - 1] = true
            diag2[x + y] = true

            recursive(y + 1)

            column[x] = false
            diag1[x - y + n - 1] = false
            diag2[x + y] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    column = BooleanArray(n)
    diag1 = BooleanArray(2 * n - 1)
    diag2 = BooleanArray(2 * n - 1)
    recursive(0)
    bw.append("$count").flush()
}