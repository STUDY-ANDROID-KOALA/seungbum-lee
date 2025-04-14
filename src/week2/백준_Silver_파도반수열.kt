package week2

private val bw = System.`out`.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val dp = LongArray(101)
    dp[1] = 1
    dp[2] = 1
    dp[3] = 1
    dp[4] = 2
    for (i in 5..100) {
        dp[i] = dp[i - 1] + dp[i - 5]
    }
    repeat(n) {
        bw.write("${dp[readLine().toInt()]}\n")
    }

    bw.flush()
    bw.close()
}
// 결국 3면을 다사용하게 되는데