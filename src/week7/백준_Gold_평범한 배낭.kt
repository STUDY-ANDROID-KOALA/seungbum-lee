package week7

/*
    배낭에 넣을 N개의 물건이 있음
    각 물건은 무게 W와 가치 V를 가지는데 해당 물건을 배낭에 넣어서가면 준서가 V만큼 즐긴다.
    최대 K만큼의 무게만을 넣을 수 있따.
    준서가 최대한 즐거운 여행을 하기위해 배낭에 넣을수 잇는 ㅁ루건의 가치의 최댓겂
    i일때 경우의 수가 점점 많아짐
    만약 이전 값이 무엇을 쓴지에따라서 더할 수 잇는게 달라지니까
    그렇다면 각 dp에서 사용여부를 어떻게 판별할까?
    구별해야하는건 (무게 : 가치) 해당 무게를 사용하면 사용못함
    그리고 무게를쓸떈 가치가가장 높은걸 사용해야한다.
    처음값 : N , K
    dp[i] = 무게가 i일때 최대 가치

    테이블
    dp[2] = dp[1] + w == 1중 제일 큰것 or dp[2]
    dp[3] = dp[2] + w==1중 제일 큰것 or dp[3] dp[1] + w==2중 제일 큰것
 */

private val bw = System.`out`.bufferedWriter()

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val w = Array(n + 1) { 0 }
    val v = Array(n + 1) { 0 }
    val dp = Array(n + 1) { Array(k + 1) { 0 } }

    repeat(n) { idx ->
        val (iw, iv) = readLine().split(" ").map { it.toInt() }
        w[idx + 1] = iw
        v[idx + 1] = iv
    }

    for (i in 1..n) {
        for (j in 1..k) {
            if (w[i] > j) {
                dp[i][j] = dp[i - 1][j]
            } else {
                dp[i][j] = maxOf(v[i] + dp[i - 1][j - w[i]], dp[i - 1][j])
            }
        }
    }

    println(dp[n][k])
}